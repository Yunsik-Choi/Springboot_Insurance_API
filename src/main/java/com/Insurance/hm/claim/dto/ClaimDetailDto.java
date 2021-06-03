package com.Insurance.hm.claim.dto;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.compensation.dto.CompensationInfoDto;
import com.Insurance.hm.contract.dto.ContractInfoDto;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
import com.Insurance.hm.partner.dto.PartnerInfoDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClaimDetailDto {

    private Long id;
    private LocalDateTime accidentDate;
    private Long damageCost;
    private String claimReason;
    private String claimDetail;
    private Double claimRate;
    private Double partnerScore;
    private LocalDateTime receiptDate;
    private ClaimStatus status;

    private ContractInfoDto contract;
    private EmployeeInfoDto employee;
    private CompensationInfoDto compensation;
    private List<PartnerInfoDto> partnerList;

    public ClaimDetailDto(Claim claim) {
        this.id = claim.getId();
        this.accidentDate = claim.getAccidentDate();
        this.damageCost = claim.getDamageCost();
        this.claimReason = claim.getClaimReason();
        this.claimDetail = claim.getClaimDetail();
        this.claimRate = claim.getClaimRate();
        this.partnerScore = claim.getPartnerScore();
        this.receiptDate = claim.getCreatedDate();
        this.status = claim.getStatus();
        if(claim.getCompensation()!=null){
            this.compensation = new CompensationInfoDto(claim.getCompensation());
        }
        this.contract = new ContractInfoDto(claim.getContract());
        this.employee = new EmployeeInfoDto(claim.getEmployee());
        this.partnerList = claim.getClaimpartnerList().stream()
                .map(i -> new PartnerInfoDto(i.getPartner())).collect(Collectors.toList());
    }
}
