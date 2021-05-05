package com.Insurance.hm.employee.domain;

import com.Insurance.hm.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String name;
    private String login_id;
    private String password;
    private String phone_number;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Department department;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
