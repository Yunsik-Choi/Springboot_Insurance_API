package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.dto.DetailEmployeeDto;
import com.Insurance.hm.employee.dto.JoinEmployeeDto;
import com.Insurance.hm.employee.dto.LoginEmployeeDto;
import com.Insurance.hm.employee.dto.LoginInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "api/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @PostMapping(value = "join")
    public void joinEmployee(@RequestBody JoinEmployeeDto joinEmployeeDto, HttpServletResponse response) throws IOException {
        System.out.println(joinEmployeeDto.toString());
        Employee joinEmployee = employeeService.join(Employee.builder()
                .name(joinEmployeeDto.getName())
                .login_id(joinEmployeeDto.getLoginId())
                .password(joinEmployeeDto.getPassword())
                .phone_number(joinEmployeeDto.getPhoneNumber())
                .email(joinEmployeeDto.getEmail())
                .department(joinEmployeeDto.getDepartment())
                .role(joinEmployeeDto.getRole())
                .build()
        );
        log.info(joinEmployee.getLogin_id()+" 저장 성공");
        response.sendRedirect("/api/employee/"+joinEmployee.getId());
    }

    @PostMapping(value = "login")
    public LoginEmployeeDto loginEmployee(@RequestBody LoginInfoDto loginInfoDto, HttpSession session){
        LoginEmployeeDto loginEmployeeDto = employeeService.login(loginInfoDto);
        session.setAttribute("loginEmployeeDto",loginEmployeeDto);
        return loginEmployeeDto;
    }

    @RequestMapping(value = "{id}")
    public DetailEmployeeDto showDetailEmployeeById(@PathVariable Long id){
        Employee findEmployee = employeeRepository.findById(id);
        DetailEmployeeDto detailEmployeeDto = new DetailEmployeeDto(findEmployee);
        return detailEmployeeDto;
    }

}
