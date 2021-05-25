package com.Insurance.hm.client.domain;

import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.global.domain.AccidentHistory;
import com.Insurance.hm.global.domain.BaseTime;
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
    private int age;
    @Column(name = "account_number")
    private String accountNumber;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Bank bank;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    @Embedded
    private RRN rrn;

    @Column(name = "contranct_list")
    @OneToMany(mappedBy = "client")
    private List<Contract> contractList = new ArrayList<>();
    @Column(name = "accident_histroy_list")
    @OneToMany(mappedBy = "client")
    private List<AccidentHistory> accidentHistoryList = new ArrayList<>();

    @Builder
    public Client(String name, int age, String accountNumber, String address, String phoneNumber, String email, Bank bank,
                  Gender gender, RRN rrn) {
        this.name = name;
        this.age = age;
        this.accountNumber = accountNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bank = bank;
        this.gender = gender;
        this.rrn = rrn;
    }

    protected void addClientInfo(String name, int age, String account_number, String address, String phone_number, String email,
                                 Bank bank, Gender gender, RRN rrn) {
        this.name = name;
        this.age = age;
        this.accountNumber = account_number;
        this.address = address;
        this.phoneNumber = phone_number;
        this.email = email;
        this.bank = bank;
        this.gender = gender;
        this.rrn = rrn;
    }
}
