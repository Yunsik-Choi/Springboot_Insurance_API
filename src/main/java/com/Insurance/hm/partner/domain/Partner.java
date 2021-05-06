package com.Insurance.hm.partner.domain;

import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.domain.ClaimPartner;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.partner.domain.entity.PartnerCategory;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Partner extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Long id;

    private String name;
    private String address;
    private String contact_number;

    @Enumerated(value = EnumType.STRING)
    private PartnerCategory category;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "partner")
    private List<ClaimPartner> claim_partner_list = new ArrayList<>();

    @Builder
    public Partner(String name, String address, String contact_number, PartnerCategory category, Employee employee, List<ClaimPartner> claim_partner_list) {
        this.name = name;
        this.address = address;
        this.contact_number = contact_number;
        this.category = category;
        this.employee = employee;
        this.claim_partner_list = claim_partner_list;
    }
}
