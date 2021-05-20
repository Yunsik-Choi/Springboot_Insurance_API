package com.Insurance.hm.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmployeeLoginRequestDto {

    private String loginId;
    private String password;

}
