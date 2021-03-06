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
    public void ??????_????????????_??????() throws Exception{
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
    public void ??????_??????_??????() throws Exception{
        ContractChangeStatusRequestDto changeStatusRequestDto = new ContractChangeStatusRequestDto();
        changeStatusRequestDto.setStatus(ContractStatus.?????????);
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
                                fieldWithPath("status").type(JsonFieldType.STRING).description("contract ??????")
                        ),
                        responseFields(
                                GlobalTestFields.getFieldResponseContract()
                        )
                ));

    }

    @Test
    public void ??????_??????() throws Exception{
        when(contractService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(
                delete("/api/contract/{id}",1L)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("Contract-delete",
                        ApiDocumentUtils.getDocumentRequest(),
                        ApiDocumentUtils.getDocumentResponse(),
                        responseFields(
                                GlobalTestFields.getFieldResponseDelete("????????? ?????? ?????????")
                        )
                ));

    }

    @Test
    public void ??????_??????_?????????() throws Exception{
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
                                fieldWithPath("information.information").type(JsonFieldType.STRING).description("???????????? ?????????"),
                                fieldWithPath("information.level").type(JsonFieldType.STRING).description("???????????? ?????? ex) ????????????, ???????????????"),
                                fieldWithPath("status").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("channel").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("contractDate.registerDate").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                fieldWithPath("contractDate.startDate").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                fieldWithPath("contractDate.endDate").type(JsonFieldType.STRING).description("?????? ?????? ??????"),
                                fieldWithPath("clientId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                fieldWithPath("insuranceId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
                                fieldWithPath("employeeId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????")
                        )
                ));

    }


}
