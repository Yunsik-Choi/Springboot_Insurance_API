package com.Insurance.hm.insurance.domain.entity.category.travel;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravellerInsurance extends Insurance {

    @Enumerated(value = EnumType.STRING)
    private TravelAreaGrade travel_area_grade;

    @Builder
    public TravellerInsurance(String name, String description, InsuranceTarget target, Employee employee, TravelAreaGrade travel_area_grade) {
        super.addInsuranceInfo(name, description, target, employee);
        this.travel_area_grade = travel_area_grade;
    }
}
