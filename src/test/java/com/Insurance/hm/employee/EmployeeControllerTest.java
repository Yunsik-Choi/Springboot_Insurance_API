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
    void ??????_????????????_??????_API() throws Exception{
        //given
        //when
        when(employeeService.findById(1L)).thenReturn(GlobalTestObject.getEmployee());
        ResultActions result = this.mockMvc.perform(
                get("/api/employee/{id}",1L)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("employee-findById",
                        getDocumentResponse(),
                        responseFields(
                                getResponseDetailEmployee()
                        )
                ));
    }

    private FieldDescriptor[] getResponseDetailEmployee() {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP ??????"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("?????????"),
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("?????? ?????????"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("?????? ????????? ?????????"),
                fieldWithPath("data.password").type(JsonFieldType.STRING).description("?????? ????????????"),
                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("?????? ????????????"),
                fieldWithPath("data.email").type(JsonFieldType.STRING).description("?????? ?????????"),
                fieldWithPath("data.department").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.role").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.createTime").type(JsonFieldType.NULL).description("?????? ?????? ??????")};
    }

    @Test
    void ??????_?????????_API() throws Exception{
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
                .andDo(document("employee-login",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("loginId").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("????????????")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP ??????"),
                                fieldWithPath("result").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("data.id").type(JsonFieldType.NULL).description("?????? ?????????"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("?????? ????????? ?????????"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("?????? ????????????"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("?????? ?????????"),
                                fieldWithPath("data.department").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("data.role").type(JsonFieldType.STRING).description("?????? ??????")
                        )

                ));
    }

    @Test
    void ??????_??????_API() throws Exception{
        //given
        //when
        when(employeeService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(delete("/api/employee/{id}", 1L));
        //then
        result.andExpect(status().isOk())
                .andDo(document("employee-delete",
                        getDocumentResponse(),
                        responseFields(
                                getResponseFieldDeleteEmployee()
                        )
                    )
                );
    }

    private FieldDescriptor[] getResponseFieldDeleteEmployee() {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP ??????"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("?????????"),
                fieldWithPath("data").type(JsonFieldType.NUMBER).description("????????? ?????? ?????????")};
    }

    @Test
    void ??????_??????_API() throws Exception {
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
        result.andExpect(status().isFound()).andDo(document("employee-join",
                getDocumentRequest(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                        fieldWithPath("loginId").type(JsonFieldType.STRING).description("?????? ????????? ?????????"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("?????? ????????????"),
                        fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("?????? ????????????"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("?????? ?????????"),
                        fieldWithPath("department").type(JsonFieldType.STRING).description("?????? ??????"),
                        fieldWithPath("role").type(JsonFieldType.STRING).description("?????? ??????")
                )
                ));
    }



}