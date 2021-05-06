package com.Insurance.hm.employee.dto;


import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.Data;

@Data
public class LoginEmployeeDto {

    private String name;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;

    public LoginEmployeeDto(Employee employee){
        this.name = employee.getName();
        this.phoneNumber = employee.getPhone_number();
        this.email = employee.getEmail();
        this.department = employee.getDepartment();
        this.role = employee.getRole();
    }

}
