package com.Insurance.hm.employee.service;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.dto.EmployeeCompensationDto;
import com.Insurance.hm.employee.dto.EmployeeJoinRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;

import java.util.List;

public interface EmployeeService {

    Long join(EmployeeJoinRequestDto joinRequestDto);

    Employee login(EmployeeLoginRequestDto loginRequestDto);

    Employee findById(Long id);

    Long deleteById(Long id);

    List<Employee> findAll();

    List<Employee> findByDepartment(Department department);

    List<EmployeeCompensationDto> findCompensation();
}
