package com.Insurance.hm.insurance.dto.employee;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InsuranceEmployeeDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;


    public InsuranceEmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.phoneNumber = employee.getPhone_number();
        this.email = employee.getEmail();
        this.department = employee.getDepartment();
        this.role = employee.getRole();
    }
}
