package com.Insurance.hm.contract.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter @Setter
public class AdditionalInformation {

    private String information;

    @Enumerated(value = EnumType.STRING)
    private ObjectLevel level;

}
