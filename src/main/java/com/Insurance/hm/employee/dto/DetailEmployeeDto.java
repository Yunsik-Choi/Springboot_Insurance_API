package com.Insurance.hm.employee.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailEmployeeDto {

    private Long id;
    private String name;
    private String loginId;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;
    private LocalDateTime createTime;

    public DetailEmployeeDto(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.loginId = employee.getLogin_id();
        this.phoneNumber = employee.getPhone_number();
        this.email = employee.getEmail();
        this.department = employee.getDepartment();
        this.role = employee.getRole();
        this.createTime = employee.getCreated_date();
    }
}
