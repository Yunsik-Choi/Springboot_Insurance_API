package com.Insurance.hm.employee;

import com.Insurance.hm.employee.constants.EmployeeResponseConstants;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.EmployeeDetailDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginResponseDto;
import com.Insurance.hm.employee.service.EmployeeService;
import com.Insurance.hm.employee.service.EmployeeServiceImpl;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import com.Insurance.hm.util.ApiDocumentUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.Insurance.hm.util.ApiDocumentUtils.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EmployeeController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext ctx;
    @MockBean
    EmployeeService employeeService;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(print())
                .build();
    }

    @Test
    void 직원_아이디로_찾기_API() throws Exception{
        //given
        Employee employee = getEmployee();

        //when
        when(employeeService.findById(1L)).thenReturn(new EmployeeDetailDto(employee));
        ResultActions result = this.mockMvc.perform(
                get("/api/employee/{id}",1L)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("Employee 아이디로 찾기",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("http status"),
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                fieldWithPath("data.id").type(JsonFieldType.NULL).description("직원 아이디"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("직원 이름"),
                                fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("직원 로그인 아이디"),
                                fieldWithPath("data.password").type(JsonFieldType.STRING).description("직원 비밀번호"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("직원 전화번호"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("직원 이메일"),
                                fieldWithPath("data.department").type(JsonFieldType.STRING).description("직원 부서"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("직원 직급"),
                                fieldWithPath("data.createTime").type(JsonFieldType.NULL).description("직원 생성 시간")
                        )
                ));
    }


    @Test
    void 직원_로그인_API() throws Exception{
        //given
        Employee employee = getEmployee();

        EmployeeLoginRequestDto loginRequestDto = new EmployeeLoginRequestDto();
        loginRequestDto.setLoginId(employee.getLogin_id());
        loginRequestDto.setPassword(employee.getPassword());

        EmployeeLoginResponseDto employeeLoginResponseDto = new EmployeeLoginResponseDto(employee);

        //when
        when(employeeService.login(loginRequestDto)).thenReturn(employeeLoginResponseDto);

        ResultActions result = this.mockMvc.perform(
                post("/api/employee/login")
                        .content(objectMapper.writeValueAsString(loginRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("Employee 로그인",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("loginId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("http status"),
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("직원 이름"),
                                fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("직원 로그인 아이디"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("직원 전화번호"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("직원 이메일"),
                                fieldWithPath("data.department").type(JsonFieldType.STRING).description("직원 부서"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("직원 직급")
                        )

                ));
    }

    private Employee getEmployee() {
        return Employee.builder()
                    .name("최윤식")
                    .login_id("abcd")
                    .password("1234")
                    .phone_number("010-000-000")
                    .email("choiys")
                    .department(Department.개발)
                    .role(Role.과장)
                    .build();
    }

    private RequestFieldsSnippet getEmployeeRequest() {
        return requestFields(
                fieldWithPath("id").type(JsonFieldType.NULL).description("아이디"),
                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                fieldWithPath("login_id").type(JsonFieldType.STRING).description("로그인 아이디"),
                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                fieldWithPath("phone_number").type(JsonFieldType.STRING).description("전화번호"),
                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                fieldWithPath("department").type(JsonFieldType.STRING).description("부서"),
                fieldWithPath("role").type(JsonFieldType.STRING).description("부서"),
                fieldWithPath("created_date").type(JsonFieldType.NULL).description("생성시간"),
                fieldWithPath("modified_date").type(JsonFieldType.NULL).description("수정시간")
        );
    }

}