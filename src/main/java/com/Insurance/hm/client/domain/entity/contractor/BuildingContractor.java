package com.Insurance.hm.client.domain.entity.contractor;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingContractor extends Client {

    @Column(name = "building_number")
    private String buildingNumber;

    @Builder(builderMethodName = "makeBuildingContractor")
    public BuildingContractor(String name, int age, String account_number, String address, String phone_number, String email,
                              Bank bank, Gender gender, RRN rrn) {
        super.addClientInfo(name, age, account_number, address, phone_number, email, bank, gender, rrn);
        this.buildingNumber = buildingNumber;
    }
}
