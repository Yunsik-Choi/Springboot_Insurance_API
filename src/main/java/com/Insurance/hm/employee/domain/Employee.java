package com.Insurance.hm.employee.domain;

import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.JoinEmployeeDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String name;
    @Column(unique = true)
    private String login_id;
    private String password;
    private String phone_number;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Department department;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public Employee(String name, String login_id, String password, String phone_number, String email, Department department, Role role) {
        this.name = name;
        this.login_id = login_id;
        this.password = password;
        this.phone_number = phone_number;
        this.email = email;
        this.department = department;
        this.role = role;
    }
}
