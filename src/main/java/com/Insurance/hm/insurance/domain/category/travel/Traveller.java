package com.Insurance.hm.insurance.domain.category.travel;

import com.Insurance.hm.insurance.domain.Insurance;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Traveller extends Insurance {

    @Enumerated(value = EnumType.STRING)
    private TravelAreaGrade grade;
}
