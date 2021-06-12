package com.Insurance.hm.contract;

import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.contract.dto.ContractChangeStatusRequestDto;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;
import com.Insurance.hm.contract.service.ContractService;
import com.Insurance.hm.util.ApiDocumentUtils;
import com.Insurance.hm.util.GlobalTestFields;
import com.Insurance.hm.util.GlobalTestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


//@WebMvcTest(controllers = ContractController.class)
@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class ContractControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext ctx;
    @MockBean
    ContractService contractService;


    @BeforeEach
    public void setup(RestDocumentationContextProvider restDocument){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocument))
                .alwaysDo(print())
                .build();

    }

    @Test
    public void 계약_아이디로_조회() throws Exception{
        ContractSignRequestDto contractSignRequestDto = GlobalTestObject.getContractSignRequestDto();

        when(contractService.findById(1L)).thenReturn(GlobalTestObject.getContract());
        ResultActions result = mockMvc.perform(
                get("/api/contract/{id}",1L)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("Contract-findById",
                        ApiDocumentUtils.getDocumentResponse(),
                        responseFields(
                                GlobalTestFields.getFieldResponseContract()
                        )
                ));

    }

    @Test
    public void 계약_상태_변경() throws Exception{
        ContractChangeStatusRequestDto changeStatusRequestDto = new ContractChangeStatusRequestDto();
        changeStatusRequestDto.setStatus(ContractStatus.계약중);
        when(contractService.changeContractStatusById(1L,changeStatusRequestDto)).thenReturn(GlobalTestObject.getContract());
        ResultActions result = mockMvc.perform(
                post("/api/contract/{id}/status",1L)
                .content(objectMapper.writeValueAsString(changeStatusRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("Contract-status",
                        ApiDocumentUtils.getDocumentRequest(),
                        ApiDocumentUtils.getDocumentResponse(),
                        requestFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("contract 상태")
                        ),
                        responseFields(
                                GlobalTestFields.getFieldResponseContract()
                        )
                ));

    }

    @Test
    public void 계약_삭제() throws Exception{
        when(contractService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(
                delete("/api/contract/{id}",1L)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("Contract-delete",
                        ApiDocumentUtils.getDocumentRequest(),
                        ApiDocumentUtils.getDocumentResponse(),
                        responseFields(
                                GlobalTestFields.getFieldResponseDelete("삭제된 계약 아이디")
                        )
                ));

    }

    @Test
    public void 계약_생성_테스트() throws Exception{
        ContractSignRequestDto contractSignRequestDto = GlobalTestObject.getContractSignRequestDto();

        when(contractService.findById(1L)).thenReturn(GlobalTestObject.getContract());
        ResultActions result = mockMvc.perform(
                post("/api/contract/sign")
                        .content(objectMapper.writeValueAsString(contractSignRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(document("Contract-sign",
                        ApiDocumentUtils.getDocumentRequest(),
                        requestFields(
                                fieldWithPath("insurancePremium").type(JsonFieldType.NUMBER).description("보험료"),
                                fieldWithPath("accumulatedPremium").type(JsonFieldType.NUMBER).description("총 납입 보험료"),
                                fieldWithPath("premiumRate").type(JsonFieldType.NUMBER).description("보험 요율"),
                                fieldWithPath("information.information").type(JsonFieldType.STRING).description("부가정보"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("계약 상태"),
                                fieldWithPath("channel").type(JsonFieldType.STRING).description("계약 채널"),
                                fieldWithPath("contractDate.registerDate").type(JsonFieldType.STRING).description("계약 등록 일자"),
                                fieldWithPath("contractDate.startDate").type(JsonFieldType.STRING).description("계약 시작 일자"),
                                fieldWithPath("contractDate.endDate").type(JsonFieldType.STRING).description("계약 종료 일자"),
                                fieldWithPath("clientId").type(JsonFieldType.NUMBER).description("계약 고객 아이디"),
                                fieldWithPath("insuranceId").type(JsonFieldType.NUMBER).description("계약 상품 아이디"),
                                fieldWithPath("employeeId").type(JsonFieldType.NUMBER).description("계약 직원 아이디")
                        )
                ));

    }


}
