package com.Insurance.hm.global.domain.claimPatner;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.partner.domain.Partner;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClaimPartner extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_partner_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    @NotNull
    private Claim claim;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    @NotNull
    private Partner partner;

    @Builder
    public ClaimPartner(Claim claim, Partner partner) {
        changeClaim(claim);
        changePartner(partner);
    }

    public void changeClaim(Claim claim){
        this.claim = claim;
        List<ClaimPartner> claimpartnerList = claim.getClaimpartnerList();
        if(!claimpartnerList.contains(this))
            claimpartnerList.add(this);
    }

    public void changePartner(Partner partner){
        this.partner = partner;
        List<ClaimPartner> claimpartnerList = partner.getClaimpartnerList();
        if(!claimpartnerList.contains(this))
            claimpartnerList.add(this);
    }
}
