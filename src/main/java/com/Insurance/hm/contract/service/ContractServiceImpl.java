package com.Insurance.hm.contract.service;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.ClientRepository;
import com.Insurance.hm.contract.constants.ContractErrorConstants;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.ContractRepository;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.contract.dto.ContractChangeStatusRequestDto;
import com.Insurance.hm.contract.dto.ContractDetailDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.InsuranceRepository;
import com.Insurance.hm.insurance.service.InsuranceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Contract contract = contractSignRequestDto.toEntity(client,insurance,employee);
        Contract saveContract = contractRepository.save(contract);
        return saveContract.getId();
    }

    @Override
    public Contract findById(Long id) {
        Contract findContract = contractRepository.findById(id).orElseThrow(() -> findContractByIdIsNull());
        return findContract;
    }

    @Override
    public Long deleteById(Long id) {
        Contract findContract = contractRepository.findById(id).orElseThrow(() -> findContractByIdIsNull());
        contractRepository.delete(findContract);
        return id;
    }

    @Override
    public Contract changeContractStatusById(ContractChangeStatusRequestDto changeStatusRequestDto) {
        Contract findContract = contractRepository.findById(changeStatusRequestDto.getId())
                                .orElseThrow(() -> findContractByIdIsNull());
        findContract.changeStatus(changeStatusRequestDto.getStatus());
        return findContract;
    }

    public BusinessException findContractByIdIsNull(){
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("Contract"));
    }
}