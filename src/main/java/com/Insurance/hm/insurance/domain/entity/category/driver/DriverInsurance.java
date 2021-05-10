package com.Insurance.hm.insurance.domain.entity.category.driver;

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
public class DriverInsurance extends Insurance {

    private int driver_grade;

    @Builder
    public DriverInsurance(String name, String description, InsuranceTarget target, Employee employee, int driver_grade) {
        super.addInsuranceInfo(name, description, target, employee);
        this.driver_grade = driver_grade;
    }

}
