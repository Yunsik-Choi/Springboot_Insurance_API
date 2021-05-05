package com.Insurance.hm.contract.domain;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    private Long insurance_premium;
    private Long accumulated_premium;
    private Double premium_rate;
    private Long reimbursement_cost;

    @Enumerated(value = EnumType.STRING)
    private ContractStatus status;
    @Enumerated(value = EnumType.STRING)
    private Channel channel;

    @Embedded
    private ContractDate contract_date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;
    @ManyToOne
    @JoinColumn(name = "insurance_id")
    @NotNull
    private Insurance insurance;
    @OneToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

}
