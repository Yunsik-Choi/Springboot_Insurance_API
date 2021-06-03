package com.Insurance.hm.claim;

import com.Insurance.hm.claim.constants.ClaimResponseConstants;
import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.dto.*;
import com.Insurance.hm.claim.service.ClaimService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/claim")
public class ClaimController{

    private final ClaimService claimService;

    @GetMapping
    public ResponseDto showAll(){
        List<Claim> all = claimService.findAll();
        return ResponseDto.builder()
                .message(ClaimResponseConstants.FIND_ALL.getMessage())
                .data(all)
                .build();
    }

    @PostMapping("create")
    public void createClaim(@RequestBody ClaimCreateRequestDto claimCreateRequestDto,
                                    HttpServletResponse response) throws IOException {
        Long id = claimService.create(claimCreateRequestDto);
        response.sendRedirect(id.toString());
    }

    @GetMapping("{id}")
    public ResponseDto findClaimById(@PathVariable(value = "id") Long id){
        Claim findClaim = claimService.findById(id);
        ClaimDetailDto claimDetailDto = new ClaimDetailDto(findClaim);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(claimDetailDto)
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteClaimById(@PathVariable(value = "id") Long id){
        claimService.deleteById(id);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(id)
                .build();
    }

    @PostMapping("{id}/status")
    public void changeClaimStatus(@PathVariable(value = "id") Long id,
                                   @RequestBody ClaimChangeStatusRequestDto changeStatusRequestDto,
                                  HttpServletResponse response) throws IOException {
        Long findId = claimService.changeClaimStatus(id, changeStatusRequestDto);
        response.sendRedirect("/api/claim/"+findId.toString());
    }

    @PostMapping("{id}/score")
    public ResponseDto changeClaimPartnerScore(@PathVariable(value = "id") Long id,
                                         @RequestBody ClaimChangePartnerScoreDto changePartnerScoreDto){
        Claim claim = claimService.changeClaimPartnerScore(id, changePartnerScoreDto);
        return ResponseDto.builder()
                .message(ClaimResponseConstants.CLAIM_NO.getMessage()+id+ ClaimResponseConstants.CHANGE_PARTNER_SCORE.getMessage())
                .data(new ClaimDetailDto(claim))
                .build();
    }

    @PostMapping("{id}/partner")
    public void addClaimPartner(@PathVariable(value = "id") Long id, @RequestBody ClaimAddPartnerDto claimAddPartnerDto,
                                 HttpServletResponse response) throws IOException {
        Long findId = claimService.addClaimPartner(id, claimAddPartnerDto);
        response.sendRedirect("/api/claim/"+findId.toString());
    }

}
