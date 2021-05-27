package com.Insurance.hm.employee;

import com.Insurance.hm.employee.constants.EmployeeResponseConstants;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.EmployeeDetailDto;
import com.Insurance.hm.employee.dto.EmployeeJoinRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginRequestDto;
import com.Insurance.hm.employee.dto.EmployeeLoginResponseDto;
import com.Insurance.hm.employee.service.EmployeeService;
import com.Insurance.hm.employee.service.EmployeeServiceImpl;
import com.Insurance.hm.global.constants.GlobalConstants;
import com.Insurance.hm.global.dto.ResponseDto;
import com.Insurance.hm.util.ApiDocumentUtils;
import com.Insurance.hm.util.GlobalTestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.Insurance.hm.util.ApiDocumentUtils.*;
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
        //when
        when(employeeService.findById(1L)).thenReturn(GlobalTestObject.getEmployee());
        ResultActions result = this.mockMvc.perform(
                get("/api/employee/{id}",1L)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("Employee 아이디로 조회",
                        getDocumentResponse(),
                        responseFields(
                                getResponseDetailEmployee()
                        )
                ));
    }

    private FieldDescriptor[] getResponseDetailEmployee() {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 상태"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("직원 아이디"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("직원 이름"),
                fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("직원 로그인 아이디"),
                fieldWithPath("data.password").type(JsonFieldType.STRING).description("직원 비밀번호"),
                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("직원 전화번호"),
                fieldWithPath("data.email").type(JsonFieldType.STRING).description("직원 이메일"),
                fieldWithPath("data.department").type(JsonFieldType.STRING).description("직원 부서"),
                fieldWithPath("data.role").type(JsonFieldType.STRING).description("직원 직급"),
                fieldWithPath("data.createTime").type(JsonFieldType.NULL).description("직원 생성 시간")};
    }

    @Test
    void 직원_로그인_API() throws Exception{
        //given
        Employee employee = GlobalTestObject.getEmployee();
        EmployeeLoginRequestDto loginRequestDto = new EmployeeLoginRequestDto();
        loginRequestDto.setLoginId(employee.getLoginId());
        loginRequestDto.setPassword(employee.getPassword());
        //when
        when(employeeService.login(loginRequestDto)).thenReturn(employee);
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
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 상태"),
                                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
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

    @Test
    void 직원_삭제_API() throws Exception{
        //given
        //when
        when(employeeService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(delete("/api/employee/{id}", 1L));
        //then
        result.andExpect(status().isOk())
                .andDo(document("Employee 삭제",
                        getDocumentResponse(),
                        responseFields(
                                getResponseFieldDeleteEmployee()
                        )
                    )
                );
    }

    private FieldDescriptor[] getResponseFieldDeleteEmployee() {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 상태"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                fieldWithPath("data").type(JsonFieldType.NUMBER).description("삭제된 직원 아이디")};
    }

    @Test
    @Disabled
    void 직원_가입_API() throws Exception {
        //given
        EmployeeJoinRequestDto joinRequestDto = new EmployeeJoinRequestDto();
        joinRequestDto.setName(GlobalTestObject.getEmployee().getName());
        joinRequestDto.setLoginId(GlobalTestObject.getEmployee().getLoginId());
        joinRequestDto.setPassword(GlobalTestObject.getEmployee().getPassword());
        joinRequestDto.setPhoneNumber(GlobalTestObject.getEmployee().getPhoneNumber());
        joinRequestDto.setEmail(GlobalTestObject.getEmployee().getEmail());
        joinRequestDto.setDepartment(GlobalTestObject.getEmployee().getDepartment());
        joinRequestDto.setRole(GlobalTestObject.getEmployee().getRole());
        //when
        when(employeeService.join(joinRequestDto)).thenReturn(1L);
        ResultActions result = mockMvc.perform(post("/api/employee/join")
                .content(objectMapper.writeValueAsString(joinRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk()).andDo(document("Employee 가입",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("직원 이름"),
                        fieldWithPath("loginId").type(JsonFieldType.STRING).description("직원 로그인 아이디"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("직원 비밀번호"),
                        fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("직원 전화번호"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("직원 이메일"),
                        fieldWithPath("department").type(JsonFieldType.STRING).description("직원 부서"),
                        fieldWithPath("role").type(JsonFieldType.STRING).description("직원 직급")
                ),
                responseFields(
                        getResponseDetailEmployee()
                )
                ));
    }



}