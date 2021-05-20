package com.Insurance.hm.client.domain.entity.contractor;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import com.Insurance.hm.contract.domain.Contract;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravellerContractor extends Client {

    private String passport_number;

    @Builder(builderMethodName = "makeTravellerContractor")
    protected TravellerContractor(String name, int age, String account_number, String address, String phone_number, String email,
                                  Bank bank, Gender gender, RRN rrn) {
        super.addClientInfo(name, age, account_number, address, phone_number, email, bank, gender, rrn);
        this.passport_number = passport_number;
    }
}
