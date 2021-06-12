package com.Insurance.hm.contract.domain.entity;

import lombok.Getter;

@Getter
public enum ObjectLevel {

    A(1,1),B(1.1,3),C(1.3,10),D(1.5,50);

    Double car;
    Double fire;
    ObjectLevel(double car, double fire) {
        this.car = car;
        this.fire = fire;
    }
}
