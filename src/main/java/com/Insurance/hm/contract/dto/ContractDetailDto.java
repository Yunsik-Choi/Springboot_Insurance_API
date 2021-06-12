package com.Insurance.hm.contract.dto;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.dto.ClientInfoDto;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.*;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import com.Insurance.hm.insurance.dto.InsuranceInfoDto;
import lombok.Data;

@Data
public class ContractDetailDto {

    private Long id;
    private Long insurancePremium;
    private Long accumulatedPremium;
    private Double premiumRate;
    private AdditionalInformation information;

    private ContractStatus status;
    private Channel channel;
    private ContractDate contractDate;

    private ClientInfoDto client;
    private InsuranceInfoDto insurance;
    private EmployeeInfoDto employee;

    public ContractDetailDto(Contract contract) {
        this.id = contract.getId();
        this.insurancePremium = contract.getInsurancePremium();
        this.accumulatedPremium = contract.getAccumulatedPremium();
        this.premiumRate = contract.getPremiumRate();
        this.information = contract.getAdditionalInformation();
        this.status = contract.getStatus();
        this.channel = contract.getChannel();
        this.contractDate = contract.getContractDate();
        this.client = new ClientInfoDto(contract.getClient());
        this.insurance = new InsuranceInfoDto(contract.getInsurance());
        this.employee = new EmployeeInfoDto(contract.getEmployee());
    }
}
