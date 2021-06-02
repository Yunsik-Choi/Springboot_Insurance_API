package com.Insurance.hm.claim.domain;

import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
import com.Insurance.hm.employee.domain.Employee;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    @Column(name = "claim_reason")
    private String claimReason;
    @Column(name = "claim_detail")
    private String claimDetail;
    @Column(name = "claim_rate")
    private Double claimRate;
    @Column(name = "partner_score")
    @Min(0)
    @Max(5)
    private Double partnerScore;
    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;

    @Enumerated(value = EnumType.STRING)
    private ClaimStatus status;

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
    public Claim(LocalDateTime accidentDate, Long damageCost, String claimReason, String claimDetail, Double claimRate,
                 Double partnerScore,LocalDateTime receiptDate, ClaimStatus status, Contract contract, Employee employee) {
        this.accidentDate = accidentDate;
        this.damageCost = damageCost;
        this.claimReason = claimReason;
        this.claimDetail = claimDetail;
        this.claimRate = claimRate;
        this.partnerScore = partnerScore;
        this.receiptDate = receiptDate;
        this.status = status;
        this.contract = contract;
        this.employee = employee;
    }

    public void changeStatus(ClaimStatus status){
        this.status = status;
    }

    public void changePartnerScore(Double partnerScore){
        this.partnerScore = partnerScore;
    }

}
