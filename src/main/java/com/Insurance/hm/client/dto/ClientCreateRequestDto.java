package com.Insurance.hm.client.dto;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import lombok.Data;

@Data
public class ClientCreateRequestDto {
    private String name;
    private int age;
    private String email;
    private String address;
    private String phoneNumber;
    private String accountNumber;
    private Bank bank;
    private Gender gender;
    private RRN rrn;

    public Client toEntity(){
        Client client = Client.builder()
                .name(name)
                .age(age)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .accountNumber(accountNumber)
                .bank(bank)
                .gender(gender)
                .rrn(rrn)
                .build();
        return client;
    }

}
