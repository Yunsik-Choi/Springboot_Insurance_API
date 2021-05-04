package com.Insurance.hm.insurance.domain.category.fire;

import com.Insurance.hm.insurance.domain.Insurance;

import javax.persistence.Entity;

@Entity
public class Fire extends Insurance {

    private int buildingGrade;
}
