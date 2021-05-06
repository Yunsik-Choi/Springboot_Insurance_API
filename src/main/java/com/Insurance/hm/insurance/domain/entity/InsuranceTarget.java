package com.Insurance.hm.insurance.domain.entity;

import javax.persistence.Embeddable;
import java.util.Vector;

@Embeddable
public class InsuranceTarget {

    private int credit_rating;
    private int end_age;
    private int start_age;
    //보상 직군
    //private Vector<String> unavailable_job;


}
