package com.Insurance.hm.compensation.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "compensation")
public class CompensationImpl implements Compensation{

    @Id
    private String compensation_id;

    private Long cost;
    private LocalDateTime date_time;

    @Enumerated(EnumType.STRING)
    private CompensationStatus status;

}
