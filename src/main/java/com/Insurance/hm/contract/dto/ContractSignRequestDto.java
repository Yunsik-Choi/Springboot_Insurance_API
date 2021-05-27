package com.Insurance.hm.contract.dto;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.Channel;
import com.Insurance.hm.contract.domain.entity.ContractDate;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import lombok.Data;



@Data
public class ContractSignRequestDto {

    private Long insurancePremium;
    private Long accumulatedPremium;
    private Double premiumRate;

    private ContractStatus status;
    private Channel channel;

    private ContractDate contractDate;
    private Long clientId;
    private Long insuranceId;
    private Long employeeId;


    public Contract toEntity(Client client, Insurance insurance, Employee employee) {
        Contract contract = Contract.builder()
            .insurancePremium(insurancePremium)
            .accumulatedPremium(accumulatedPremium)
            .premiumRate(premiumRate)
            .status(status)
            .channel(channel)
            .contractDate(contractDate)
            .client(client)
            .insurance(insurance)
            .employee(employee)
            .build();
        return contract;
    }
}
