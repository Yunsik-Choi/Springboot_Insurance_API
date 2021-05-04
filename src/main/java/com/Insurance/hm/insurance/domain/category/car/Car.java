package com.Insurance.hm.insurance.domain.category.car;

import com.Insurance.hm.insurance.domain.Insurance;

import javax.persistence.Entity;

@Entity
public class Car extends Insurance {

    private int car_grade;
    private int driver_grade;


}
