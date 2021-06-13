package com.Insurance.hm.employee;

import com.Insurance.hm.employee.constants.EmployeeResponseConstants;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.dto.*;
import com.Insurance.hm.employee.service.EmployeeService;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseDto showAll(){
        List<Employee> all = employeeService.findAll();
        List<EmployeeDetailDto> responseAll = all.stream().map(i -> new EmployeeDetailDto(i)).collect(Collectors.toList());
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.FIND_ALL.getMessage())
                .data(responseAll)
                .build();
    }

    @GetMapping("{id}")
    public ResponseDto showDetailEmployeeById(@PathVariable Long id){
        Employee findEmployee = employeeService.findById(id);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO.getMessage()+id+GlobalConstants.FIND_BY_ID.getMessage())
                .data(new EmployeeDetailDto(findEmployee))
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseDto deleteEmployeeById(@PathVariable Long id){
        Long deleteId = employeeService.deleteById(id);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO.getMessage()+id+GlobalConstants.DELETE.getMessage())
                .data(deleteId)
                .build();
    }

    @PostMapping("join")
    public void joinEmployee(@RequestBody @Valid EmployeeJoinRequestDto joinEmployeeDto,
                                    HttpServletResponse response) throws IOException {
        Long id = employeeService.join(joinEmployeeDto);
        response.sendRedirect(id.toString());
    }

    @PostMapping("login")
    public ResponseDto loginEmployee(@RequestBody EmployeeLoginRequestDto loginInfoDto){
        Employee findEmployee
                = employeeService.login(loginInfoDto);
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.EMPLOYEE_NO+findEmployee.getLoginId()
                        +EmployeeResponseConstants.LOGIN.getMessage())
                .data(new EmployeeLoginResponseDto(findEmployee))
                .build();
    }

    @GetMapping("department")
    public ResponseDto findByDepartment(@RequestParam(value = "department")Department department){
        List<Employee> byDepartment = employeeService.findByDepartment(department);
        List<EmployeeDetailDto> result = byDepartment.stream().map(i -> new EmployeeDetailDto(i)).collect(Collectors.toList());
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.FIND_BY_DEPARTMENT.getMessage()+"부서 : "+department)
                .data(result)
                .build();
    }


    @GetMapping("compensation")
    public ResponseDto getDevelopment(){
        List<EmployeeCompensationDto> compensationEmployee = employeeService.findCompensation();
        return ResponseDto.builder()
                .message(EmployeeResponseConstants.FIND_WITH_COMPENSATION.getMessage())
                .data(compensationEmployee)
                .build();
    }




}
