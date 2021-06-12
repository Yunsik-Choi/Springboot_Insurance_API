package com.Insurance.hm.client.domain;

import com.Insurance.hm.client.domain.entity.*;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.global.domain.BaseTime;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String email;

    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "account_number")
    private String accountNumber;
    @Enumerated(value = EnumType.STRING)
    private Bank bank;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Job job;

    @Embedded
    private RRN rrn;

    @Column(name = "contranct_list")
    @OneToMany(mappedBy = "client")
    private Set<Contract> contractList = new HashSet<>();
    @Column(name = "accident_histroy_list")
    @OneToMany(mappedBy = "client")
    private Set<AccidentHistory> accidentHistoryList = new HashSet<>();

    @Builder
    public Client(String name, int age, String accountNumber, String address, String phoneNumber, String email, Bank bank,
                  Gender gender, Job job, RRN rrn) {
        this.name = name;
        this.age = age;
        this.accountNumber = accountNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bank = bank;
        this.gender = gender;
        this.job = job;
        this.rrn = rrn;
    }
}
