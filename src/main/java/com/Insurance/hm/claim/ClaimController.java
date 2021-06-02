package com.Insurance.hm.claim;

import com.Insurance.hm.claim.constants.ClaimResponseConstants;
import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.dto.ClaimChangePartnerScoreDto;
import com.Insurance.hm.claim.dto.ClaimChangeStatusRequestDto;
import com.Insurance.hm.claim.dto.ClaimCreateRequestDto;
import com.Insurance.hm.claim.dto.ClaimDetailDto;
import com.Insurance.hm.claim.service.ClaimService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/claim")
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping("create")
    private void createClaim(@RequestBody ClaimCreateRequestDto claimCreateRequestDto,
                                    HttpServletResponse response) throws IOException {
        Long id = claimService.create(claimCreateRequestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    private ResponseDto findClaimById(@PathVariable(value = "id") Long id){
        Claim findClaim = claimService.findById(id);
        ClaimDetailDto claimDetailDto = new ClaimDetailDto(findClaim);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(claimDetailDto)
                .build();
    }

    @DeleteMapping("{id}")
    private ResponseDto deleteClaimById(@PathVariable(value = "id") Long id){
        claimService.deleteById(id);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(id)
                .build();
    }

    @PostMapping("{id}/status")
    private ResponseDto changeClaimStatus(@PathVariable(value = "id") Long id,
                                   @RequestBody ClaimChangeStatusRequestDto changeStatusRequestDto){
        Claim claim = claimService.changeClaimStatus(id, changeStatusRequestDto);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+ ClaimResponseConstants.CHANGE_CLAIM_STATUS.getMessage())
                .data(new ClaimDetailDto(claim))
                .build();
    }

    @PostMapping("{id}/score")
    private ResponseDto changeClaimPartnerScore(@PathVariable(value = "id") Long id,
                                         @RequestBody ClaimChangePartnerScoreDto changePartnerScoreDto){
        Claim claim = claimService.changeClaimPartnerScore(id, changePartnerScoreDto);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+ ClaimResponseConstants.CHANGE_PARTNER_SCORE.getMessage())
                .data(new ClaimDetailDto(claim))
                .build();
    }





}
