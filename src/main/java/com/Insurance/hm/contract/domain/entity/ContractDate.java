package com.Insurance.hm.contract.domain.entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class ContractDate {

    private LocalDateTime register_date;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

}
