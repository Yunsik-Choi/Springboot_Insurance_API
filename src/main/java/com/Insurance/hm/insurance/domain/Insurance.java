package com.Insurance.hm.insurance.domain;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.entity.EmployeeCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Insurance extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private String id;

    private String name;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private EmployeeCategory category;

    @Embedded
    private InsuranceTarget target;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "insurance")
    private List<Contract> contract_list = new ArrayList<>();

    protected void addInsuranceInfo(String name, String description, InsuranceTarget target, Employee employee) {
        this.name = name;
        this.description = description;
        this.target = target;
        this.employee = employee;
    }
}