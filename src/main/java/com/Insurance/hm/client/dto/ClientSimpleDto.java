package com.Insurance.hm.client.dto;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import lombok.Data;

@Data
public class ClientSimpleDto {

    private Long id;
    private String name;
    private int age;
    private Gender gender;

    public ClientSimpleDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.age = client.getAge();
        this.gender = client.getGender();
    }

}
