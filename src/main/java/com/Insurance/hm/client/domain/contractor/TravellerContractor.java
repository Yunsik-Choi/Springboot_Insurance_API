package com.Insurance.hm.client.domain.contractor;

import com.Insurance.hm.client.domain.Client;

import javax.persistence.Entity;

@Entity
public class TravellerContractor extends Client {

    private String passport_number;
}
