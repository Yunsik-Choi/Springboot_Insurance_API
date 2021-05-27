package com.Insurance.hm.employee.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeInfoDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;


    public EmployeeInfoDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
        this.department = employee.getDepartment();
        this.role = employee.getRole();
    }
}
