package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.LoginEmployeeDto;
import com.Insurance.hm.employee.dto.LoginInfoDto;
import com.Insurance.hm.exception.SimpleMessageException;
import org.junit.jupiter.api.Assertions;
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
    public void 같은_아이디로_회원가입() throws Exception{
        //given
        Employee e1 = getEmployee();
        Employee e2 = getEmployee();

        //when
        employeeService.join(e1);

        //then
        org.junit.jupiter.api.Assertions.assertThrows(SimpleMessageException.class,() -> employeeService.join(e2));
    }

    @Test
    void 로그인(){
        Employee e = getEmployee();
        employeeService.join(e);

        Assertions.assertThrows(SimpleMessageException.class,
                () -> employeeService.login(new LoginInfoDto("ccc","1234")));
        Assertions.assertThrows(SimpleMessageException.class,
                () -> employeeService.login(new LoginInfoDto("abc","4444")));
        org.assertj.core.api.Assertions
                .assertThat(employeeService.login(new LoginInfoDto(e.getLogin_id(),e.getPassword())))
                .isInstanceOf(LoginEmployeeDto.class);

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
