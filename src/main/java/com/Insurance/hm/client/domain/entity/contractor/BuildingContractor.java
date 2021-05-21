package com.Insurance.hm.client.domain.entity.contractor;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
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
    public BuildingContractor(String name, int age, String account_number, String address, String phone_number, String email,
                              Bank bank, Gender gender, RRN rrn) {
        super.addClientInfo(name, age, account_number, address, phone_number, email, bank, gender, rrn);
        this.building_number = building_number;
    }
}
