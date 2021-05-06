package com.Insurance.hm.domain;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.partner.domain.Partner;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

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
        this.claim = claim;
        this.partner = partner;
    }
}
