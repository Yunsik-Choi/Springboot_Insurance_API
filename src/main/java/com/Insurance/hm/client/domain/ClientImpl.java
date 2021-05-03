package com.Insurance.hm.client.domain;

import javax.persistence.*;

@Entity(name = "client")
public class ClientImpl implements Client{

    @Id
    private String client_id;

    private String name;
    private String account_number;
    private String address;

    @Enumerated(value = EnumType.STRING)
    private Bank bank;
    @Embedded
    private RRN rrn;


}
