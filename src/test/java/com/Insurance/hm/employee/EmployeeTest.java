package com.Insurance.hm.employee;

import com.Insurance.hm.employee.EmployeeService;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void Employee_저장() throws Exception{
        //given
        Employee employee = getEmployee();
        //when
        Employee saveEmployee = employeeRepository.save(employee);
        //then
        assertThat(saveEmployee).isNotNull()
                                .isEqualTo(employee);
    }

    @Test
    public void Employee로그인() throws Exception{
        //given
        Employee employee = getEmployee();

        //when
        Employee saveEmployee = employeeRepository.save(employee);
        Employee findEmployee = employeeService.findByLoginId(saveEmployee.getLogin_id(), saveEmployee.getPassword());

        //then
        assertThat(findEmployee).isNotNull();
        assertThat(findEmployee.getId()).isEqualTo(employee.getId());
        assertThat(findEmployee.getLogin_id()).isEqualTo(employee.getLogin_id());
        assertThat(findEmployee.getPassword()).isEqualTo(employee.getPassword());
    }

    public static Employee getEmployee() {
        return Employee.builder()
                .department(Department.개발)
                .email("abc@naver.com")
                .login_id("abc")
                .name("윤식")
                .password("1234")
                .phone_number("010-0000-0000")
                .role(Role.과장)
                .build();
    }


}
