package com.Insurance.hm.client.domain.entity.contractor;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.RRN;
import com.Insurance.hm.contract.domain.Contract;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriverContractor extends Client {

    private String driver_license_number;

    @Builder(builderMethodName = "makeDriverContractor")
    protected DriverContractor(String name, String account_number, String address, Bank bank, RRN rrn, List<Contract> contract_list, String driver_license_number) {
        super.addClientInfo(name,account_number,address,bank,rrn,contract_list);
        this.driver_license_number = driver_license_number;
    }
}
