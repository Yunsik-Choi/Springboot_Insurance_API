package com.Insurance.hm.client.domain.entity.contractor;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.RRN;
import com.Insurance.hm.contract.domain.Contract;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingContractor extends Client {

    private String building_number;

    @Builder(builderMethodName = "makeBuildingContractor")
    public BuildingContractor(String name, String account_number, String address, Bank bank, RRN rrn, List<Contract> contract_list, String building_number) {
        super.addClientInfo(name,account_number,address,bank,rrn,contract_list);
        this.building_number = building_number;
    }
}
