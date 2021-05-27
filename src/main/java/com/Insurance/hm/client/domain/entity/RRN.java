package com.Insurance.hm.client.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class RRN {

    @Column(name = "rrn_front")
    private int rrnFront;
    @Column(name = "rrn_back")
    private int rrnBack;

}
