package com.Insurance.hm.insurance.domain;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import com.Insurance.hm.insurance.dto.InsuranceUpdateRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NamedEntityGraph(
        name = "insurance-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("createEmployee"),
                @NamedAttributeNode("managementEmployee"),
                @NamedAttributeNode("contractList"),
        }
)
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
    private String coverage;
    private String registerDocument;
    private String accidentDocument;
    private Long basePrice;

    @Enumerated(value = EnumType.STRING)
    private InsuranceCategory category;
    @Enumerated(value = EnumType.STRING)
    private InsuranceStatus status;

    @Embedded
    private InsuranceTarget target;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_employee_id")
    private Employee createEmployee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "management_employee_id")
    private Employee managementEmployee;

    @OneToMany(mappedBy = "insurance")
    private List<Contract> contractList = new ArrayList<>();

    @Builder
    public Insurance(String name, String description, InsuranceCategory category, String coverage,
                     String registerDocument, String accidentDocument, Long basePrice,
                     InsuranceStatus status, InsuranceTarget target, Employee createEmployee, Employee managementEmployee) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.coverage = coverage;
        this.registerDocument = registerDocument;
        this.accidentDocument = accidentDocument;
        this.basePrice = basePrice;
        this.status = status;
        this.target = target;
        this.createEmployee = createEmployee;
        this.managementEmployee = managementEmployee;
    }

    public void changeStatus(InsuranceStatus status) {
        this.status = status;
    }

    public void updateInsurance(InsuranceUpdateRequestDto requestDto, Employee employee){
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.coverage = requestDto.getCoverage();
        this.registerDocument = requestDto.getRegisterDocument();
        this.accidentDocument = requestDto.getAccidentDocument();
        this.basePrice = requestDto.getBasePrice();
        this.category = requestDto.getCategory();
        this.target = requestDto.getTarget();
        this.managementEmployee = employee;
    }
}