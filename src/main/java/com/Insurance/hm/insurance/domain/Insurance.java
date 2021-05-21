package com.Insurance.hm.insurance.domain;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
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
    private Long id;

    private String name;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private InsuranceCategory category;

    @Embedded
    private InsuranceTarget target;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_employee_id")
    private Employee create_employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "management_employee_id")
    private Employee management_employee;

    @OneToMany(mappedBy = "insurance")
    private List<Contract> contract_list = new ArrayList<>();

    @Builder
    public Insurance(String name, String description, InsuranceCategory category, InsuranceTarget target,
                     Employee create_employee, Employee management_employee) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.target = target;
        this.create_employee = create_employee;
        this.management_employee = management_employee;
    }
}