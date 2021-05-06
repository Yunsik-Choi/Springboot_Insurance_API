package com.Insurance.hm.insurance.domain.entity.category.car;

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
public class CarInsurance extends Insurance {

    private int car_grade;
    private int driver_grade;

    @Builder
    public CarInsurance(String name, String description, InsuranceTarget target, Employee employee, List<Contract> contract_list, int car_grade, int driver_grade) {
        super.addInsuranceInfo(name, description, target, employee, contract_list);
        this.car_grade = car_grade;
        this.driver_grade = driver_grade;
    }


}
