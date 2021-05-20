package com.Insurance.hm.global.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.partner.domain.Partner;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimPartner extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claimpartner_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    @NotNull
    private Claim claim;
    @ManyToOne
    @JoinColumn(name = "partner")
    @NotNull
    private Partner partner;

    @Builder
    public ClaimPartner(Claim claim, Partner partner) {
        changeClaim(claim);
        changePartner(partner);
    }

    public void changeClaim(Claim claim){
        this.claim = claim;
        List<ClaimPartner> claimpartner_list = claim.getClaimpartner_list();
        if(!claimpartner_list.contains(this))
            claimpartner_list.add(this);
    }

    public void changePartner(Partner partner){
        this.partner = partner;
        List<ClaimPartner> claimpartner_list = partner.getClaimpartner_list();
        if(!claimpartner_list.contains(this))
            claimpartner_list.add(this);
    }
}
