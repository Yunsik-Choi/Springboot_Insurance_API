package com.Insurance.hm.insurance.domain;

import javax.persistence.*;

@Entity(name = "insurance")
public class InsuranceImpl implements Insurance {

    @Id
    private String insurance_id;

    private String name;
    private String description;

    //사고시 제출 서류
    //private File accident_documents;
    //보상범위
    //private File liability_coverages;

    @Enumerated(value = EnumType.STRING)
    private InsuranceType type;

    @Embedded
    private InsuranceTarget target;

}
