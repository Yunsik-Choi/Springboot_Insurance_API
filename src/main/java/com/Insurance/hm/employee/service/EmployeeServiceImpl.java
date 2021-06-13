package com.Insurance.hm.employee.service;

import com.Insurance.hm.employee.constants.EmployeeErrorConstants;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.dto.EmployeeJoinRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;
import com.Insurance.hm.employee.exception.*;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Long join(EmployeeJoinRequestDto joinRequestDto){
        Optional findEmployee = employeeRepository.findEmployeeByLoginId(joinRequestDto.getLoginId());
        if(findEmployee.isPresent())
            throw new AlreadyTakenLoginIdException(EmployeeErrorConstants.Already_Exists_LoginId);
        Long employeeId = employeeRepository.save(joinRequestDto.toEntity()).getId();
        return employeeId;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee login(EmployeeLoginRequestDto loginRequestDto) {
        Employee findEmployee = employeeRepository.findEmployeeByLoginId(loginRequestDto.getLoginId())
                .orElseThrow(() -> new NonExistLoginIdException(EmployeeErrorConstants.Non_Exists_loginId));
        if(!findEmployee.getPassword().equals(loginRequestDto.getPassword()))
            throw new NonMatchPasswordException(EmployeeErrorConstants.Non_Match_Password);
        return findEmployee;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id){
        Employee findEmployee = employeeRepository.findById(id).orElseThrow(() -> findEmployeeByIdIsNull());
        return findEmployee;
    }

    @Override
    public Long deleteById(Long id) {
        Employee findEmployee = employeeRepository.findById(id).orElseThrow(() -> findEmployeeByIdIsNull());
        employeeRepository.delete(findEmployee);
        return findEmployee.getId();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> all = employeeRepository.findAll();
        return all;
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        List<Employee> employeeByDepartment = employeeRepository.findEmployeeByDepartment(department);
        return employeeByDepartment;
    }

    private BusinessException findEmployeeByIdIsNull() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("Employee"));
    }

}
