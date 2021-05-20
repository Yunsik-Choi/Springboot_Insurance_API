package com.Insurance.hm.compensation.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Compensation extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compensation_id")
    private Long id;

    private Long cost;
    private LocalDateTime date_time;

    @Enumerated(EnumType.STRING)
    private CompensationStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id")
    @NotNull
    private Claim claim;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @NotNull
    private Contract contract;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @Builder
    public Compensation(Long cost, LocalDateTime date_time, CompensationStatus status, Claim claim, Contract contract, Employee employee) {
        this.cost = cost;
        this.date_time = date_time;
        this.status = status;
        this.claim = claim;
        this.contract = contract;
        this.employee = employee;
    }
}
