package com.Insurance.hm.client.domain.contractor;

import com.Insurance.hm.client.domain.Client;

import javax.persistence.Entity;

@Entity
public class DriverContractor extends Client {

    private String driver_license_number;
}
