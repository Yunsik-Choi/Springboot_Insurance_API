package com.Insurance.hm.claim.service;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.dto.ClaimAddPartnerDto;
import com.Insurance.hm.claim.dto.ClaimChangePartnerScoreDto;
import com.Insurance.hm.claim.dto.ClaimChangeStatusRequestDto;
import com.Insurance.hm.claim.dto.ClaimCreateRequestDto;

import java.util.List;

public interface ClaimService {

    Long create(ClaimCreateRequestDto createRequestDto);

    Claim findById(Long id);

    Long deleteById(Long id);

    Long changeClaimStatus(Long id, ClaimChangeStatusRequestDto changeStatusRequestDto);

    Claim changeClaimPartnerScore(Long id, ClaimChangePartnerScoreDto changePartnerScoreDto);

    Long addClaimPartner(Long id, ClaimAddPartnerDto claimAddPartnerDto);

    List<Claim> findAll();
}
