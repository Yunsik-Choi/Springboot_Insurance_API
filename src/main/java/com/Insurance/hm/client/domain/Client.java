package com.Insurance.hm.client.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.domain.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Builder
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
    private List<Contract> contract_list;

}
