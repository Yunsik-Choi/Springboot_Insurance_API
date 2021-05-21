package com.Insurance.hm.insurance.service;

import com.Insurance.hm.employee.service.EmployeeService;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.InsuranceRepository;
import com.Insurance.hm.insurance.dto.InsuranceCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService{

    private final InsuranceRepository insuranceRepository;
    private final EmployeeService employeeService;

    @Override
    public Long create(InsuranceCreateRequestDto createRequestDto) {
        Insurance createInsurance  = insuranceRepository.create(
                    createRequestDto.toEntity(
                            employeeService.findById(createRequestDto.getCreateEmployeeId()),
                            employeeService.findById(createRequestDto.getManagementEmployeeId()))
        );
        return createInsurance.getId();
    }

    @Override
    public Insurance findById(Long id) {
        Insurance findInsurance = insuranceRepository.findById(id);
        findInsuranceByIdIsNull(findInsurance);
        return findInsurance;
    }

    @Override
    public Long deleteById(Long id) {
        Insurance findInsurance = insuranceRepository.findById(id);
        findInsuranceByIdIsNull(findInsurance);
        insuranceRepository.deleteById(id);
        return findInsurance.getId();
    }

    private void findInsuranceByIdIsNull(Insurance findInsurance) {
        if(findInsurance==null)
            throw new NonMatchIdException(GlobalErrorConstants.Non_Match_Id);
    }

}
