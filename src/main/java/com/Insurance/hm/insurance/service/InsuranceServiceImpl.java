package com.Insurance.hm.insurance.service;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.Channel;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.insurance.constants.InsuranceErrorConstants;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.InsuranceRepository;
import com.Insurance.hm.insurance.dto.InsuranceChangeStatusRequestDto;
import com.Insurance.hm.insurance.dto.InsuranceCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService{

    private final InsuranceRepository insuranceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Long create(InsuranceCreateRequestDto createRequestDto) {
        Employee createEmployee = employeeRepository.findById(createRequestDto.getCreateEmployeeId())
                .orElseThrow(() -> new NonMatchIdException(InsuranceErrorConstants.Non_Match_Create_Employee_Exception));
        Employee managementEmployee = employeeRepository.findById(createRequestDto.getManagementEmployeeId())
                .orElseThrow(() -> new NonMatchIdException(InsuranceErrorConstants.Non_Match_Management_Employee_Exception));
        Insurance createInsurance  = insuranceRepository.save(createRequestDto.toEntity(createEmployee,managementEmployee));
        return createInsurance.getId();
    }

    @Override
    public Insurance findById(Long id) {
        Insurance findInsurance = insuranceRepository.findById(id).orElseThrow(() -> findInsuranceByIdIsNull());
        return findInsurance;
    }

    @Override
    public Long deleteById(Long id) {
        Insurance findInsurance = insuranceRepository.findById(id).orElseThrow(() -> findInsuranceByIdIsNull());
        insuranceRepository.deleteById(id);
        return findInsurance.getId();
    }

    @Override
    public Insurance changeStatus(Long id, InsuranceChangeStatusRequestDto changeStatusRequestDto) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(this::findInsuranceByIdIsNull);
        insurance.changeStatus(changeStatusRequestDto.getStatus());
        return insurance;
    }

    @Override
    public List<Insurance> findAll() {
        List<Insurance> all = insuranceRepository.findAll();
        return all;
    }

    private BusinessException findInsuranceByIdIsNull() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("Insurance"));
    }

}
