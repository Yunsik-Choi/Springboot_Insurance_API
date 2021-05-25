package com.Insurance.hm.employee.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeJoinRequestDto {

    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;

    public Employee toEntity(){
        Employee employee = Employee.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .phoneNumber(phoneNumber)
                .email(email)
                .department(department)
                .role(role)
                .build();
        return employee;
    }

}
