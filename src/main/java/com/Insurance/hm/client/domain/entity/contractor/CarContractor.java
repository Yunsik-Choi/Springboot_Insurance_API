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
public class CarContractor extends Client {

    private String car_number;

    @Builder(builderMethodName = "makeCarContractor")
    protected CarContractor(String name, String account_number, String address, String phone_number, String email,
                            Bank bank, RRN rrn, List<Contract> contract_list) {
        super.addClientInfo(name, account_number, address, phone_number, email, bank, rrn, contract_list);
        this.car_number = car_number;
    }
}
