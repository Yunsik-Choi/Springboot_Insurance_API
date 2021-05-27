package com.Insurance.hm.client.domain.entity.contractor;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriverContractor extends Client {

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Builder(builderMethodName = "makeDriverContractor")
    protected DriverContractor(String name, int age, String account_number, String address, String phone_number, String email,
                               Bank bank, Gender gender, RRN rrn) {
        super.addClientInfo(name, age, account_number, address, phone_number, email, bank, gender, rrn);
        this.driverLicenseNumber = driverLicenseNumber;
    }
}
