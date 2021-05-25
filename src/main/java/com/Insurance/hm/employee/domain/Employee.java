package com.Insurance.hm.employee.domain;

import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
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
    @Column(name = "login_id",unique = true)
    private String loginId;
    private String password;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Department department;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public Employee(String name, String loginId, String password, String phoneNumber, String email, Department department, Role role) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        this.role = role;
    }
}
