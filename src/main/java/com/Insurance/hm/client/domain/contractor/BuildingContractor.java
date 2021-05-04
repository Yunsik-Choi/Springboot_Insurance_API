package com.Insurance.hm.client.domain.contractor;

import com.Insurance.hm.client.domain.Client;

import javax.persistence.Entity;

@Entity
public class BuildingContractor extends Client {

    private String building_number;
}
