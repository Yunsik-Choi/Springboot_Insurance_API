package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.dto.LoginEmployeeDto;
import com.Insurance.hm.employee.dto.LoginInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "api/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "join")
    public Employee joinEmployee(@RequestBody Employee employee){
        Employee joinEmployee = employeeService.join(employee);
        return joinEmployee;
    }


    @PostMapping(value = "login")
    public LoginEmployeeDto loginEmployee(@RequestBody LoginInfoDto loginInfoDto, HttpSession session){
        LoginEmployeeDto loginEmployeeDto = employeeService.login(loginInfoDto);
        session.setAttribute("loginEmployeeDto",loginEmployeeDto);
        return loginEmployeeDto;
    }

}
