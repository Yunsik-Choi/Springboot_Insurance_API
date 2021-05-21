package com.Insurance.hm.insurance.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Vector;

@Embeddable
@Setter @Getter
public class InsuranceTarget {

    @Column(name = "credit_rating")
    private int creditRating;
    @Column(name = "start_age")
    private int startAge;
    @Column(name = "end_age")
    private int endAge;

}
