package com.Insurance.hm.claim.domain;

import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.claim.domain.entity.ClaimType;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.global.domain.ClaimPartner;
import com.Insurance.hm.employee.domain.Employee;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Claim extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    @Column(name = "accident_date")
    private LocalDateTime accidentDate;
    @Column(name = "damage_cost")
    private Long damageCost;

    @Column(name = "hospital_statement")
    private String hospitalStatement;
    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;

    @Enumerated(value = EnumType.STRING)
    private ClaimStatus status;
    @Enumerated(value = EnumType.STRING)
    private ClaimType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @NotNull
    private Contract contract;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @OneToMany(mappedBy = "claim")
    @Column(name = "claimpartner_list")
    private List<ClaimPartner> claimpartnerList = new ArrayList<>();

    @Builder
    public Claim(LocalDateTime accidentDate, Long damageCost, String hospitalStatement, LocalDateTime receiptDate,
                 ClaimStatus status, ClaimType type, Contract contract, Employee employee) {
        this.accidentDate = accidentDate;
        this.damageCost = damageCost;
        this.hospitalStatement = hospitalStatement;
        this.receiptDate = receiptDate;
        this.status = status;
        this.type = type;
        this.contract = contract;
        this.employee = employee;
    }

}
