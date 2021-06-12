package com.Insurance.hm.contract.service;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.ClientRepository;
import com.Insurance.hm.client.domain.entity.Job;
import com.Insurance.hm.contract.constants.ContractErrorConstants;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.ContractRepository;
import com.Insurance.hm.contract.domain.entity.AdditionalInformation;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.contract.domain.entity.ObjectLevel;
import com.Insurance.hm.contract.dto.ContractChangeStatusRequestDto;
import com.Insurance.hm.contract.exception.BanTravelAreaException;
import com.Insurance.hm.contract.exception.IncorrectInsuranceStatusForSign;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.InsuranceRepository;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService{

    private final ContractRepository contractRepository;
    private final InsuranceRepository insuranceRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    @Override
    public Long sign(ContractSignRequestDto contractSignRequestDto) {
        Client client = clientRepository.findById(contractSignRequestDto.getClientId())
                .orElseThrow(() -> new NonMatchIdException(ContractErrorConstants.Non_Match_Client));
        Insurance insurance = insuranceRepository.findById(contractSignRequestDto.getInsuranceId())
                .orElseThrow(() -> new NonMatchIdException(ContractErrorConstants.Non_Match_Insurance));
        Employee employee = employeeRepository.findById(contractSignRequestDto.getEmployeeId())
                .orElseThrow(() -> new NonMatchIdException(ContractErrorConstants.Non_Match_Employee));
        if (!insurance.getStatus().equals(InsuranceStatus.결재완료))
            throw new IncorrectInsuranceStatusForSign(ContractErrorConstants.INCORRECT_INSURANCE_STATUS_FOR_SIGN);
        Contract contract = contractSignRequestDto.toEntity(client,insurance,employee);
        if(client.getCreditRating()>insurance.getTarget().getCreditRating())
            contract.changeStatus(ContractStatus.계약거부);
        if(client.getAge()<insurance.getTarget().getStartAge()&&client.getAge()>insurance.getTarget().getEndAge())
            contract.changeStatus(ContractStatus.계약거부);
        Contract saveContract = contractRepository.save(contract);
        return saveContract.getId();
    }

    @Override
    public Contract findById(Long id) {
        Contract findContract = contractRepository.findById(id).orElseThrow(this::findContractByIdIsNull);
        return findContract;
    }

    @Override
    public Long deleteById(Long id) {
        Contract findContract = contractRepository.findById(id).orElseThrow(this::findContractByIdIsNull);
        contractRepository.delete(findContract);
        return id;
    }

    @Override
    public Contract changeContractStatusById(Long id, ContractChangeStatusRequestDto changeStatusRequestDto) {
        Contract findContract = contractRepository.findById(id).orElseThrow(this::findContractByIdIsNull);
        findContract.changeStatus(changeStatusRequestDto.getStatus());
        return findContract;
    }

    @Override
    public Long autoExamineRate(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(this::findContractByIdIsNull);
        Client client = contract.getClient();
        InsuranceCategory category = contract.getInsurance().getCategory();
        Double rate = null;
        if(category.equals(InsuranceCategory.자동차)||category.equals(InsuranceCategory.운전자))
            rate = examineCarRate(client, contract.getAdditionalInformation());
        else if(category.equals(InsuranceCategory.화재))
            rate = examineFireRate(contract.getAdditionalInformation());
        else if(category.equals(InsuranceCategory.여행))
            rate = examineTravelRate(contract,contract.getAdditionalInformation());
        contract.changePremiumRate(rate);
        contract.changeInsurancePremium((long) (contract.getInsurancePremium()*rate));
        return id;
    }

    private Double examineTravelRate(Contract contract, AdditionalInformation additionalInformation) {
        Double rate = 1.0;
        ObjectLevel level = additionalInformation.getLevel();
        if(level.equals(ObjectLevel.B))
            rate*=2;
        else if(level.equals(ObjectLevel.C))
            rate*=3;
        else if(level.equals(ObjectLevel.D)) {
            contract.changeStatus(ContractStatus.계약거부);
            throw new BanTravelAreaException(ContractErrorConstants.BAN_TRAVEL_AREA);
        }
        return rate;
    }

    private Double examineFireRate(AdditionalInformation additionalInformation) {
        Double rate = 1.0;
        ObjectLevel level = additionalInformation.getLevel();
        if(level.equals(ObjectLevel.B))
            rate*=3;
        else if(level.equals(ObjectLevel.C))
            rate*=10;
        else if(level.equals(ObjectLevel.D))
            rate*=50;
        return rate;
    }

    private Double examineCarRate(Client client, AdditionalInformation additionalInformation) {
        Double rate = 1.0;
        int age = client.getAge();
        if(age>=20||age<30)
            rate*=1.3;
        else if(age<40)
            rate*=1.1;
        else if(age<50)
            rate*=1;
        else
            rate*=1.1;
        Job job = client.getJob();
        if(job.equals(Job.서비스직_운전)||job.equals(Job.운송))
            rate*=1.5;
        else if(job.equals(Job.서비스직_여행))
            rate*=1.1;
        ObjectLevel level = additionalInformation.getLevel();
        if(level.equals(ObjectLevel.B))
            rate*=1.1;
        else if(level.equals(ObjectLevel.C))
            rate*=1.3;
        else if(level.equals(ObjectLevel.D))
            rate*=1.5;
        return rate;
    }

    @Override
    public List<Contract> findAll() {
        List<Contract> all = contractRepository.findAll();
        return all;
    }

    public BusinessException findContractByIdIsNull(){
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("Contract"));
    }
}
