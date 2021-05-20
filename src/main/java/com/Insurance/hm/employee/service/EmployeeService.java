package com.Insurance.hm.employee.service;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.dto.EmployeeDetailDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginResponseDto;

public interface EmployeeService {

    Long join(Employee employee);

    EmployeeLoginResponseDto login(EmployeeLoginRequestDto loginRequestDto);

    EmployeeDetailDto findById(Long id);

    Long deleteById(Long id);
}
