package com.Insurance.hm.partner.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity(name = "partner")
public class PartnerImpl implements Partner{

    @Id
    private String id;

    private String name;
    private String address;
    private String contact_number;

    @Enumerated(value = EnumType.STRING)
    private PartnerCategory category;


}
