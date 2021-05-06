package com.Insurance.hm.insurance.domain.entity.category.fire;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FireInsurance extends Insurance {

    private int building_grade;

    @Builder
    public FireInsurance(String name, String description, InsuranceTarget target, Employee employee, List<Contract> contract_list, int building_grade) {
        super.addInsuranceInfo(name, description, target, employee, contract_list);
        this.building_grade = building_grade;
    }
}
