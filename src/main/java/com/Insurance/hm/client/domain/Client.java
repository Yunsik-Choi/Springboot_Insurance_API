package com.Insurance.hm.client.domain;

import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.RRN;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.domain.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Client extends BaseTime {

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
    private List<Contract> contract_list = new ArrayList<>();

    @Builder
    public Client(String name, String account_number, String address, Bank bank, RRN rrn, List<Contract> contract_list) {
        this.name = name;
        this.account_number = account_number;
        this.address = address;
        this.bank = bank;
        this.rrn = rrn;
        this.contract_list = contract_list;
    }

    protected void addClientInfo(String name, String account_number, String address, Bank bank, RRN rrn, List<Contract> contract_list) {
        this.name = name;
        this.account_number = account_number;
        this.address = address;
        this.bank = bank;
        this.rrn = rrn;
        this.contract_list = contract_list;
    }
}
