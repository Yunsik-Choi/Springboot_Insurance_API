package com.Insurance.hm.client.dto;

import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryInfoDto;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.*;
import com.Insurance.hm.contract.dto.ContractInfoDto;
import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import lombok.Data;
import net.bytebuddy.implementation.bytecode.Addition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ClientDetailDto {

    private Long id;

    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private String email;
    private String accountNumber;
    private Bank bank;
    private Gender gender;
    private Job job;
    private RRN rrn;

    private List<ContractInfoDto> contractList = new ArrayList<>();
    private List<AccidentHistoryInfoDto> accidentHistoryList = new ArrayList<>();

    public ClientDetailDto(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.address = client.getAddress();
        this.phoneNumber = client.getPhoneNumber();
        this.accountNumber = client.getAccountNumber();
        this.bank = client.getBank();
        this.gender = client.getGender();
        this.job = client.getJob();
        this.rrn = client.getRrn();

        client.getContractList().stream().forEach(contract -> contractList.add(new ContractInfoDto(contract)));
        client.getAccidentHistoryList().stream()
                .forEach(accidentHistory -> accidentHistoryList.add(new AccidentHistoryInfoDto(accidentHistory)));
    }
}
