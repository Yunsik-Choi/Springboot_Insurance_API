package com.Insurance.hm.employee.dto;

import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.dto.CompensationInfoDto;
import com.Insurance.hm.contract.dto.ContractInfoDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDevelopmentDto {

    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private Department department;
    private Role role;
    private List<CompensationInfoDto> compensationList = new ArrayList<>();

    public EmployeeDevelopmentDto(Employee i, List<Compensation> compensations) {
        this.id = i.getId();
        this.name = i.getName();
        this.loginId = i.getLoginId();
        this.password = i.getPassword();
        this.phoneNumber = i.getPhoneNumber();
        this.email = i.getEmail();
        this.department = i.getDepartment();
        this.role = i.getRole();
        if(compensations.size()>0 && compensations!=null)
            compensations.stream().forEach(compensation -> compensationList.add(new CompensationInfoDto(compensation)));
    }
}
