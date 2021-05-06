package com.Insurance.hm.insurance.domain;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class Insurance extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private String id;

    private String name;
    private String description;

    //사고시 제출 서류
    //private File accident_documents;
    //보상범위
    //private File liability_coverages;

    @Embedded
    private InsuranceTarget target;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "insurance")
    private List<Contract> contract_list = new ArrayList<>();

    protected void addInsuranceInfo(String name, String description, InsuranceTarget target, Employee employee, List<Contract> contract_list) {
        this.name = name;
        this.description = description;
        this.target = target;
        this.employee = employee;
        this.contract_list = contract_list;
    }
}