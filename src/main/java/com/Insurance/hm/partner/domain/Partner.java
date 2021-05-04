package com.Insurance.hm.partner.domain;

import com.Insurance.hm.domain.ClaimPartner;
import com.Insurance.hm.employee.domain.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
public class Partner {

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
    private List<ClaimPartner> claimpartner_list;

}
