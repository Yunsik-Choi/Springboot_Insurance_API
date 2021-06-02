package com.Insurance.hm.claim.dto;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.employee.domain.Employee;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClaimCreateRequestDto {

    private LocalDateTime accidentDate;
    private Long damageCost;
    private String claimReason;
    private String claimDetail;
    private Double claimRate;
    private Long contractId;
    private Long employeeId;

    public Claim toEntity(Contract contract, Employee employee) {
        Claim claim = Claim.builder()
                .accidentDate(accidentDate)
                .damageCost(damageCost)
                .claimReason(claimReason)
                .claimDetail(claimDetail)
                .claimRate(claimRate)
                .partnerScore(0.0)
                .receiptDate(LocalDateTime.now())
                .status(ClaimStatus.접수완료)
                .contract(contract)
                .employee(employee)
                .build();
        return claim;
    }
}
