package com.Insurance.hm.client.domain.contractor;

import com.Insurance.hm.client.domain.Client;

import javax.persistence.Entity;

@Entity
public class CarContractor extends Client {

    private String car_number;
}
