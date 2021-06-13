package com.Insurance.hm.contract.dto;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractInfoDto {

    private Long id;
    private Long insurancePremium;
    private AdditionalInformation information;
    private ContractStatus status;
    private Channel channel;
    private ContractDate contractDate;
    private Long clientId;
    private int clientAge;
    private Long insuranceId;
    private Long employeeId;

    public ContractInfoDto(Contract contract) {
        this.id = contract.getId();
        this.insurancePremium = contract.getInsurancePremium();
        this.information = contract.getAdditionalInformation();
        this.status = contract.getStatus();
        this.channel = contract.getChannel();
        this.contractDate = contract.getContractDate();
        this.clientId = contract.getClient().getId();
        this.clientAge = contract.getClient().getAge();
        this.insuranceId = contract.getInsurance().getId();
        this.employeeId = contract.getEmployee().getId();
    }
}
