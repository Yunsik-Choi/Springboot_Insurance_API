package com.Insurance.hm.insurance;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import com.Insurance.hm.insurance.dto.InsuranceChangeStatusRequestDto;
import com.Insurance.hm.insurance.dto.InsuranceCreateRequestDto;
import com.Insurance.hm.insurance.service.InsuranceService;
import com.Insurance.hm.util.ApiDocumentUtils;
import com.Insurance.hm.util.GlobalTestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.Insurance.hm.util.ApiDocumentUtils.getDocumentRequest;
import static com.Insurance.hm.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InsuranceController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class InsuranceControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext ctx;
    @MockBean
    InsuranceService insuranceService;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }

    @Test
    void ??????_????????????_??????_API() throws Exception{
        //given
        Insurance insurance = getInsurance();
        insurance.getContractList().add(GlobalTestObject.getContract());

        //when
        when(insuranceService.findById(1L)).thenReturn(insurance);
        ResultActions result = this.mockMvc.perform(get("/api/insurance/{id}",1L));

        //then
        result.andExpect(status().isOk())
            .andDo(document("Insurance-findById",
                    getDocumentResponse(),
                    responseFields(
                            getResponseDetailInsuranceDto()
                    )
            ));
    }

    @Test
    void ??????_??????() throws Exception {

        //when
        when(insuranceService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(delete("/api/insurance/{id}", 1L));

        //then
        result.andExpect(status().isOk()).andDo(document("Insurance-delete",
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP ??????"),
                        fieldWithPath("result").type(JsonFieldType.STRING).description("?????? ??????"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("?????????"),
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("????????? ?????? ?????????")
                )
        ));

    }

    @Test
    void ??????_??????_??????() throws Exception{
        InsuranceChangeStatusRequestDto changeStatusRequestDto = new InsuranceChangeStatusRequestDto();
        changeStatusRequestDto.setStatus(InsuranceStatus.????????????);
        when(insuranceService.changeStatus(1L,changeStatusRequestDto)).thenReturn(getInsurance());
        ResultActions result = mockMvc.perform(post("/api/insurance/{id}/status",1L)
                .content(objectMapper.writeValueAsString(changeStatusRequestDto))
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk()).andDo(document("insurance-status",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("status").description(JsonFieldType.STRING).description("?????? ??????")
                ),
                responseFields(
                        getResponseDetailInsuranceDto()
                )
                ));
    }

    @Test
    void ??????_??????_API() throws Exception{
        InsuranceCreateRequestDto createRequestDto = new InsuranceCreateRequestDto();
        Insurance insurance = getInsurance();
        createRequestDto.setName(insurance.getName());
        createRequestDto.setTarget(insurance.getTarget());
        createRequestDto.setCoverage(insurance.getCoverage());
        createRequestDto.setBasePrice(insurance.getBasePrice());
        createRequestDto.setRegisterDocument(insurance.getRegisterDocument());
        createRequestDto.setAccidentDocument(insurance.getAccidentDocument());
        createRequestDto.setCategory(insurance.getCategory());
        createRequestDto.setDescription(insurance.getDescription());
        createRequestDto.setCreateEmployeeId(insurance.getCreateEmployee().getId());
        createRequestDto.setManagementEmployeeId(insurance.getManagementEmployee().getId());

        ResultActions result = mockMvc.perform(post("/api/insurance/create")
                .content(objectMapper.writeValueAsString(createRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isFound())
                .andDo(document("Insurance-create",
                        getDocumentRequest(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("coverage").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                fieldWithPath("registerDocument").type(JsonFieldType.STRING).description("?????? ????????? ?????? ??????"),
                                fieldWithPath("accidentDocument").type(JsonFieldType.STRING).description("?????? ????????? ?????? ??????"),
                                fieldWithPath("basePrice").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("target.creditRating").type(JsonFieldType.NUMBER).description("?????? ?????? ?????? ????????????"),
                                fieldWithPath("target.startAge").type(JsonFieldType.NUMBER).description("?????? ?????? ?????? ??????"),
                                fieldWithPath("target.endAge").type(JsonFieldType.NUMBER).description("?????? ?????? ?????? ??????"),
                                fieldWithPath("createEmployeeId").type(JsonFieldType.NULL).description("?????? ????????? ?????? ?????????"),
                                fieldWithPath("managementEmployeeId").type(JsonFieldType.NULL).description("?????? ???????????? ?????? ?????????")
                        )
                        ));
    }





    private FieldDescriptor[] getResponseDetailInsuranceDto() {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP ??????"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("?????????"),
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("?????? ?????????"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.description").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.coverage").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                fieldWithPath("data.registerDocument").type(JsonFieldType.STRING).description("?????? ????????? ?????? ??????"),
                fieldWithPath("data.accidentDocument").type(JsonFieldType.STRING).description("?????? ????????? ?????? ??????"),
                fieldWithPath("data.basePrice").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                fieldWithPath("data.category").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("?????? ??????"),
                fieldWithPath("data.target.creditRating").type(JsonFieldType.NUMBER).description("?????? ????????????"),
                fieldWithPath("data.target.startAge").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                fieldWithPath("data.target.endAge").type(JsonFieldType.NUMBER).description("?????? ?????? ??????"),
                fieldWithPath("data.createTime").type(JsonFieldType.NULL).description("?????? ?????? ??????"),
                fieldWithPath("data.modifiedTime").type(JsonFieldType.NULL).description("?????? ?????? ??????"),
                subsectionWithPath("data.createEmployee").type(JsonFieldType.OBJECT).description("?????? ????????? ??????"),
                subsectionWithPath("data.managementEmployee").type(JsonFieldType.OBJECT).description("?????? ???????????? ??????"),
                subsectionWithPath("data.contractList").type(JsonFieldType.ARRAY).description("?????? ?????? ?????? ?????????").optional()};
    }

    private InsuranceCreateRequestDto getInsuranceCreateDto() {
        Insurance insurance = getInsurance();
        InsuranceCreateRequestDto createRequestDto = new InsuranceCreateRequestDto();
        createRequestDto.setName(insurance.getName());
        createRequestDto.setDescription(insurance.getDescription());
        createRequestDto.setCategory(insurance.getCategory());
        createRequestDto.setTarget(insurance.getTarget());
        createRequestDto.setCreateEmployeeId(getEmployee().getId());
        createRequestDto.setManagementEmployeeId(getEmployee().getId());
        return createRequestDto;
    }

    private Insurance getInsurance() {
        Insurance insurance = Insurance.builder()
                .name("HM ??????")
                .description("?????? ??????")
                .coverage("?????? ??????")
                .registerDocument("????????? ?????? ??????")
                .accidentDocument("????????? ?????? ??????")
                .basePrice(100000L)
                .category(InsuranceCategory.?????????)
                .status(InsuranceStatus.????????????)
                .target(getInsuranceTarget())
                .createEmployee(getEmployee())
                .managementEmployee(getEmployee())
                .build();
        return insurance;
    }

    private InsuranceTarget getInsuranceTarget() {
        InsuranceTarget insuranceTarget = new InsuranceTarget();
        insuranceTarget.setCreditRating(3);
        insuranceTarget.setEndAge(70);
        insuranceTarget.setStartAge(24);
        return insuranceTarget;
    }

    private Employee getEmployee() {
        return Employee.builder()
                .name("??????")
                .loginId("abcd")
                .password("1234")
                .phoneNumber("010-000-000")
                .email("abcd")
                .department(Department.??????)
                .role(Role.??????)
                .build();
    }
}