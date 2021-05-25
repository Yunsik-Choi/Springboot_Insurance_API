package com.Insurance.hm.employee.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeDetailDto {

    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;
    private LocalDateTime createTime;

    public EmployeeDetailDto(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.loginId = employee.getLoginId();
        this.password = employee.getPassword();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.department = employee.getDepartment();
        this.role = employee.getRole();
        this.createTime = employee.getCreatedDate();
    }
}
