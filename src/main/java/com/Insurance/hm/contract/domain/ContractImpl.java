package com.Insurance.hm.contract.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "contract")
public class ContractImpl implements Contract{

    @Id
    private String id;

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

}
