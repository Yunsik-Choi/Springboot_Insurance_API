package com.Insurance.hm.compensation.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Builder
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

    @OneToOne
    @JoinColumn(name = "claim_id")
    @NotNull
    private Claim claim;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    @NotNull
    private Contract contract;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;


}
