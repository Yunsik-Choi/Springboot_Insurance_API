package com.Insurance.hm.claim;

import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.claim.dto.ClaimAddPartnerDto;
import com.Insurance.hm.claim.dto.ClaimChangePartnerScoreDto;
import com.Insurance.hm.claim.dto.ClaimChangeStatusRequestDto;
import com.Insurance.hm.claim.service.ClaimService;
import com.Insurance.hm.util.ApiDocumentUtils;
import com.Insurance.hm.util.GlobalTestFields;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = ClaimController.class)
class ClaimControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext ctx;
    @MockBean
    ClaimService claimService;

    @BeforeEach
    public void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }


    @Test
    void ??????_????????????_??????() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        when(claimService.findById(1L)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                get("/api/claim/{id}",1L)
        );

        result.andExpect(status().isOk()).andDo(document("claim-findById",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseClaimRequestDto()
                )
                ));
    }

    @Test
    void ??????_????????????_??????() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        when(claimService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(
                delete("/api/claim/{id}",1L)
        );

        result.andExpect(status().isOk()).andDo(document("claim-delete",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseDelete("????????? claim ?????????")
                )
        ));
    }

    @Test
    void ??????_??????_??????() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        ClaimChangeStatusRequestDto requestDto = new ClaimChangeStatusRequestDto();
        requestDto.setStatus(ClaimStatus.???????????????);
        when(claimService.changeClaimStatus(1L,requestDto)).thenReturn(1L);
        ResultActions result = mockMvc.perform(
                post("/api/claim/{id}/status",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        );

        result.andExpect(status().isFound()).andDo(document("claim-status",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                        fieldWithPath("status").type(JsonFieldType.STRING).description("?????? ?????? ??????")
                )
        ));
    }

    @Test
    void ??????_?????????_????????????() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        ClaimChangePartnerScoreDto changePartnerScoreDto = new ClaimChangePartnerScoreDto();
        changePartnerScoreDto.setScore(1.1);
        when(claimService.changeClaimPartnerScore(1L,changePartnerScoreDto)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                post("/api/claim/{id}/score",1L)
                .content(objectMapper.writeValueAsString(changePartnerScoreDto))
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk()).andDo(document("claim-score",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("score").type(JsonFieldType.NUMBER).description("?????? ??????")
                ),
                responseFields(
                        GlobalTestFields.getFieldResponseClaimRequestDto()
                )
        ));
    }

    @Test
    void ??????_?????????_??????() throws Exception{
        ClaimAddPartnerDto claimAddPartnerDto = new ClaimAddPartnerDto();
        claimAddPartnerDto.setPartnerId(1L);
        when(claimService.addClaimPartner(1L,claimAddPartnerDto)).thenReturn(1L);
        ResultActions result = mockMvc.perform(post("/api/claim/{id}/partner", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(claimAddPartnerDto))
        );
        result.andExpect(status().isFound()).andDo(document("claim-partner",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                        fieldWithPath("partnerId").type(JsonFieldType.NUMBER).description("????????? ????????? ?????????")
                )
        ));
    }



    @Test
    void ??????_??????() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        when(claimService.findById(1L)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                post("/api/claim/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(GlobalTestObject.getClaimCreateRequestDto()))
        );

        result.andExpect(status().isFound()).andDo(document("claim-create",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                        GlobalTestFields.getFieldRequestClaimRequestDto()
                )
                ));
    }

}