package com.Insurance.hm.client.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RRN {

    @Column(name = "rrn_front")
    private int rrnFront;
    @Column(name = "rrn_back")
    private int rrnBack;


}
