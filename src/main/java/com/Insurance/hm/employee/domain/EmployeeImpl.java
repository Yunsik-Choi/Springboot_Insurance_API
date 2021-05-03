package com.Insurance.hm.employee.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity(name = "employee")
public class EmployeeImpl implements Employee{

    @Id
    private Long employee_id;

    private String name;
    private String login_id;
    private String password;
    private String phone_number;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Department department;
}
