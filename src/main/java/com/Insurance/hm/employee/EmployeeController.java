package com.Insurance.hm.employee;

import com.Insurance.hm.employee.constants.EmployeeResponseConstants;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.dto.EmployeeDetailDto;
import com.Insurance.hm.employee.dto.EmployeeJoinRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginResponseDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;
import com.Insurance.hm.employee.service.EmployeeService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "api/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "{id}")
    public ResponseDto showDetailEmployeeById(@PathVariable Long id){
        Employee findEmployee = employeeService.findById(id);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(new EmployeeDetailDto(findEmployee))
                .build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseDto deleteEmployeeById(@PathVariable Long id){
        Long deleteId = employeeService.deleteById(id);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(deleteId)
                .build();
    }

    @PostMapping(value = "join")
    public void joinEmployee(@RequestBody @Valid EmployeeJoinRequestDto joinEmployeeDto,
                                    HttpServletResponse response) throws IOException {
        Long id = employeeService.join(joinEmployeeDto);
        response.sendRedirect(id.toString());
    }

    @PostMapping(value = "login")
    public ResponseDto loginEmployee(@RequestBody EmployeeLoginRequestDto loginInfoDto){
        Employee findEmployee
                = employeeService.login(loginInfoDto);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO+findEmployee.getLoginId()
                        +EmployeeResponseConstants.LOGIN.getMessage())
                .data(new EmployeeLoginResponseDto(findEmployee))
                .build();
    }



}
