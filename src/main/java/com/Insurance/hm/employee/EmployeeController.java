package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.dto.LoginEmployeeDto;
import com.Insurance.hm.employee.dto.LoginInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping(value = "login")
    public LoginEmployeeDto loginEmployee(@RequestBody LoginInfoDto loginInfoDto){
        System.out.println(loginInfoDto);
        Employee loginEmployee = employeeService.findByLoginId(loginInfoDto.getId(), loginInfoDto.getPassword());
        LoginEmployeeDto loginEmployeeDto = new LoginEmployeeDto(loginEmployee);
        return loginEmployeeDto;
    }

}
