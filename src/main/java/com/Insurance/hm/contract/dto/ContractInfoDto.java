package com.Insurance.hm.contract.dto;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.Channel;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import lombok.Data;

@Data
public class ContractInfoDto {

    private Long id;
    private Long insurancePremium;
    private ContractStatus status;
    private Channel channel;
    private Long clientId;
    private Long insuranceId;
    private Long employeeId;

    public ContractInfoDto(Contract contract) {
        this.id = contract.getId();
        this.insurancePremium = contract.getInsurancePremium();
        this.status = contract.getStatus();
        this.channel = contract.getChannel();
        this.clientId = contract.getClient().getId();
        this.insuranceId = contract.getInsurance().getId();
        this.employeeId = contract.getEmployee().getId();
    }
}
