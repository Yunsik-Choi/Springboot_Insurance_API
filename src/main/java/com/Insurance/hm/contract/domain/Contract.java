package com.Insurance.hm.contract.domain;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.contract.domain.entity.Channel;
import com.Insurance.hm.contract.domain.entity.ContractDate;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
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

    @Builder
    public Contract(Long insurance_premium, Long accumulated_premium, Double premium_rate, Long reimbursement_cost, ContractStatus status, Channel channel, ContractDate contract_date, Client client, Insurance insurance, Employee employee) {
        this.insurance_premium = insurance_premium;
        this.accumulated_premium = accumulated_premium;
        this.premium_rate = premium_rate;
        this.reimbursement_cost = reimbursement_cost;
        this.status = status;
        this.channel = channel;
        this.contract_date = contract_date;
        this.client = client;
        this.insurance = insurance;
        this.employee = employee;
    }
}
