package com.Insurance.hm.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.partner.domain.Partner;

import javax.persistence.*;

@Entity
public class ClaimPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claimpartner_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Claim claim;
    @ManyToOne
    @JoinColumn(name = "partner")
    private Partner partner;



}
