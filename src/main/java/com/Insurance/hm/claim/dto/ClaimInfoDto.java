package com.Insurance.hm.claim.dto;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClaimInfoDto {

    private Long id;
    private LocalDateTime accidentDate;
    private ClaimStatus status;
    private Double partnerScore;


    public ClaimInfoDto(Claim claim) {
        this.id = claim.getId();
        this.accidentDate = claim.getAccidentDate();
        this.status = claim.getStatus();
        this.partnerScore = claim.getPartnerScore();
    }
}
