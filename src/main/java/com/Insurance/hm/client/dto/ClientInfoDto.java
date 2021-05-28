package com.Insurance.hm.client.dto;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import lombok.Data;

@Data
public class ClientInfoDto {

    private Long id;

    private String name;
    private int age;
    private RRN rrn;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String email;



    public ClientInfoDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.age = client.getAge();
        this.address = client.getAddress();
        this.phoneNumber = client.getPhoneNumber();
        this.email = client.getEmail();
        this.gender = client.getGender();
        this.rrn = client.getRrn();
    }
}