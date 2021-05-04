package com.Insurance.hm.claim.domain;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.domain.ClaimPartner;
import com.Insurance.hm.employee.domain.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Claim{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    private LocalDateTime accident_date;
    private Long damage_cost;
    private String hospital_statement;
    private LocalDateTime receipt_date;
    //정비에 필요한 서류
    //private File maintenace_details;
    //현장 사진 파일
    //private File site_photos;

    @Enumerated(value = EnumType.STRING)
    private ClaimStatus status;
    @Enumerated(value = EnumType.STRING)
    private ClaimType type;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "claim")
    private List<ClaimPartner> claimpartner_list;

}
