package com.Insurance.hm.contract.domain;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.contract.domain.entity.*;
import com.Insurance.hm.global.domain.BaseTime;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@NamedEntityGraph(
        name = "contract-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("client"),
                @NamedAttributeNode("insurance"),
                @NamedAttributeNode("employee")
        }
)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    @Column(name = "insurance_premium")
    private Long insurancePremium;
    @Column(name = "accumulated_premium")
    private Long accumulatedPremium;
    @Column(name = "premium_rate")
    private Double premiumRate;
    @Column(name = "additional_Information")
    private AdditionalInformation additionalInformation;

    @Enumerated(value = EnumType.STRING)
    private ContractStatus status;
    @Enumerated(value = EnumType.STRING)
    private Channel channel;

    @Embedded
    @Column(name = "contract_date")
    private ContractDate contractDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id")
    @NotNull
    private Insurance insurance;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @Builder
    public Contract(Long insurancePremium, Long accumulatedPremium, Double premiumRate, AdditionalInformation information,
                    ContractStatus status, Channel channel, ContractDate contractDate, Client client,
                    Insurance insurance, Employee employee) {
        this.insurancePremium = insurancePremium;
        this.accumulatedPremium = accumulatedPremium;
        this.premiumRate = premiumRate;
        this.additionalInformation = information;
        this.status = status;
        this.channel = channel;
        this.contractDate = contractDate;
        this.client = client;
        changeInsurance(insurance);
        changeClient(client);
        this.employee = employee;
    }

    public void changeInsurance(Insurance insurance){
        this.insurance = insurance;
        List<Contract> contractList = insurance.getContractList();
        if(!contractList.contains(this))
            contractList.add(this);
    }
    public void changeClient(Client client){
        this.client = client;
        Set<Contract> contractList = client.getContractList();
        if(!contractList.contains(this))
            contractList.add(this);
    }

    public void changePremiumRate(Double rate){
        this.premiumRate = rate;
    }

    public void changeInsurancePremium(Long insurancePremium){
        this.insurancePremium = insurancePremium;
    }

    public void changeStatus(ContractStatus changeStatus){
        this.status = changeStatus;
    }
}
