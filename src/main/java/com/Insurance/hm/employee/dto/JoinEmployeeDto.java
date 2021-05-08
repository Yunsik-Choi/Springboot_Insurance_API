package com.Insurance.hm.employee.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinEmployeeDto {

    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;

}
