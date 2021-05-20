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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "api/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "{id}")
    public ResponseDto showDetailEmployeeById(@PathVariable Long id){
        EmployeeDetailDto employeeDetailDto = employeeService.findById(id);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO.getMessage()+id
                        +" "+GlobalConstants.FIND_BY_ID.getMessage())
                .data(employeeDetailDto)
                .build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseDto deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO.getMessage()+id
                        +" "+GlobalConstants.DELETE.getMessage())
                .data(id)
                .build();
    }

    @PostMapping(value = "join")
    public void joinEmployee(@RequestBody EmployeeJoinRequestDto joinEmployeeDto,
                                    HttpServletResponse response) throws IOException {
        Long id = employeeService.join(joinEmployeeDto.toEntity());
        response.sendRedirect(id.toString());
    }

    @PostMapping(value = "login")
    public ResponseDto loginEmployee(@RequestBody EmployeeLoginRequestDto loginInfoDto){
        EmployeeLoginResponseDto employeeLoginResponseDto = employeeService.login(loginInfoDto);
        return ResponseDto.builder()
                .message(employeeLoginResponseDto.getLoginId()+" "+EmployeeResponseConstants.LOGIN.getMessage())
                .data(employeeLoginResponseDto)
                .build();
    }



}
