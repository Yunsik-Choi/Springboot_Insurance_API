package com.Insurance.hm.employee;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.LoginEmployeeDto;
import com.Insurance.hm.employee.dto.LoginInfoDto;
import com.Insurance.hm.test.TestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WebApplicationContext ctx;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    void Employee로그인_테스트() throws Exception {

//        Employee employee = EmployeeTest.getEmployee();
//        employeeRepository.save(employee);
//
//        LoginInfoDto loginInfoDto = new LoginInfoDto("abc","1234");
//        MockHttpServletResponse model = mockMvc.perform(post("/api/employee/login/")
//                .content(mapper.writeValueAsString(loginInfoDto))
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andReturn()
//                .getResponse();

    }


}