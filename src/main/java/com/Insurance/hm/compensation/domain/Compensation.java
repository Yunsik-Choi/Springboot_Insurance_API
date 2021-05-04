package com.Insurance.hm.compensation.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.employee.domain.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Compensation {

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
    private Claim claim;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
