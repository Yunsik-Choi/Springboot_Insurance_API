package com.Insurance.hm.client.domain;

import com.Insurance.hm.contract.domain.Contract;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    private String name;
    private String account_number;
    private String address;

    @Enumerated(value = EnumType.STRING)
    private Bank bank;

    @Embedded
    private RRN rrn;

    @OneToMany(mappedBy = "client")
    private List<Contract> contract_list;

}
