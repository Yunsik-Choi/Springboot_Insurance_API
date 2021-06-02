package com.Insurance.hm.claim.dto;

import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import lombok.Data;

@Data
public class ClaimChangeStatusRequestDto {

    private ClaimStatus status;

}
