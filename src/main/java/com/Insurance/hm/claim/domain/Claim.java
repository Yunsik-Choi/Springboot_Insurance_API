package com.Insurance.hm.claim.domain;

import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.claim.domain.entity.ClaimType;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.domain.ClaimPartner;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @NotNull
    private Contract contract;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @OneToMany(mappedBy = "claim")
    private List<ClaimPartner> claimpartner_list = new ArrayList<>();

    @Builder
    public Claim(LocalDateTime accident_date, Long damage_cost, String hospital_statement, LocalDateTime receipt_date,
                 ClaimStatus status, ClaimType type, Contract contract, Employee employee) {
        this.accident_date = accident_date;
        this.damage_cost = damage_cost;
        this.hospital_statement = hospital_statement;
        this.receipt_date = receipt_date;
        this.status = status;
        this.type = type;
        this.contract = contract;
        this.employee = employee;
    }

}
