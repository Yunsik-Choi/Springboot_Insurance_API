package com.Insurance.hm.employee.service;

import com.Insurance.hm.employee.constants.EmployeeErrorConstants;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.dto.EmployeeDetailDto;
import com.Insurance.hm.employee.dto.EmployeeLoginResponseDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;
import com.Insurance.hm.employee.exception.*;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Long join(Employee employee){
        Optional findEmployee = employeeRepository.findByLoginId(employee.getLogin_id());
        if(findEmployee.isPresent())
            throw new AlreadyTakenLoginIdException(EmployeeErrorConstants.Already_Exists_LoginId);
        Employee save = employeeRepository.save(employee);
        return save.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeLoginResponseDto login(EmployeeLoginRequestDto loginInfoDto) {
        Optional<Employee> findEmployee = employeeRepository.findByLoginId(loginInfoDto.getLoginId());
        findEmployee.orElseThrow(() -> new NonExistLoginIdException(EmployeeErrorConstants.Non_Exists_loginId));
        if(!findEmployee.get().getPassword().equals(loginInfoDto.getPassword()))
            throw new NonMatchPasswordException(EmployeeErrorConstants.Non_Match_Password);
        EmployeeLoginResponseDto loginEmployeeDto = new EmployeeLoginResponseDto(findEmployee.get());
        return loginEmployeeDto;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDetailDto findById(Long id){
        Employee findEmployee = employeeRepository.findById(id);
        if(findEmployee==null)
            throw new NonMatchIdException(EmployeeErrorConstants.Non_Match_Id);
        EmployeeDetailDto employeeDetailDto = new EmployeeDetailDto(findEmployee);
        return employeeDetailDto;
    }

    @Override
    public Long deleteById(Long id) {
        Employee findEmployee = employeeRepository.findById(id);
        if(findEmployee==null)
            throw new NonMatchIdException(EmployeeErrorConstants.Non_Match_Id);
        employeeRepository.delete(findEmployee.getId());
        return id;
    }


}
