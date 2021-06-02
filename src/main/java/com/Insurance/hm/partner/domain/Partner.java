package com.Insurance.hm.partner.domain;

import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
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
    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(value = EnumType.STRING)
    private PartnerCategory category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "partner")
    @Column(name = "claimpartner_list")
    private List<ClaimPartner> claimpartnerList = new ArrayList<>();

    @Builder
    public Partner(String name, String address, String contactNumber, PartnerCategory category, Employee employee) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.category = category;
        this.employee = employee;
    }
}
