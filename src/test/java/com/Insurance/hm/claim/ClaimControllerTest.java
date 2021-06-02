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
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
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
    void 사고_아이디로_조회() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        when(claimService.findById(1L)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                get("/api/claim/{id}",1L)
        );

        result.andExpect(status().isOk()).andDo(document("claim 아이디로 조회",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseClaimRequestDto()
                )
                ));
    }

    @Test
    void 사고_아이디로_삭제() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        when(claimService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(
                delete("/api/claim/{id}",1L)
        );

        result.andExpect(status().isOk()).andDo(document("claim 아이디로 삭제",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseDelete("삭제된 claim 아이디")
                )
        ));
    }

    @Test
    void 사고_상태_변경() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        ClaimChangeStatusRequestDto requestDto = new ClaimChangeStatusRequestDto();
        requestDto.setStatus(ClaimStatus.보상심사중);
        when(claimService.changeClaimStatus(1L,requestDto)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                post("/api/claim/{id}/status",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        );

        result.andExpect(status().isOk()).andDo(document("claim 상태 변경",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("status").type(JsonFieldType.STRING).description("사고 처리 상태")
                ),
                responseFields(
                        GlobalTestFields.getFieldResponseClaimRequestDto()
                )
        ));
    }

    @Test
    void 사고_파트너_별점변경() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        ClaimChangePartnerScoreDto changePartnerScoreDto = new ClaimChangePartnerScoreDto();
        changePartnerScoreDto.setPartnerScore(1.1);
        when(claimService.changeClaimPartnerScore(1L,changePartnerScoreDto)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                post("/api/claim/{id}/score",1L)
                .content(objectMapper.writeValueAsString(changePartnerScoreDto))
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk()).andDo(document("claim 파트너 점수 변경",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("partnerScore").type(JsonFieldType.NUMBER).description("바꿀 점수")
                ),
                responseFields(
                        GlobalTestFields.getFieldResponseClaimRequestDto()
                )
        ));
    }

    @Test
    void 사고_파트너_추가() throws Exception{
        ClaimAddPartnerDto claimAddPartnerDto = new ClaimAddPartnerDto();
        claimAddPartnerDto.setPartnerId(1L);
        when(claimService.addClaimPartner(1L,claimAddPartnerDto)).thenReturn(1L);
        ResultActions result = mockMvc.perform(post("/api/claim/{id}/partner", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(claimAddPartnerDto))
        );
        result.andExpect(status().isFound()).andDo(document("claim 파트너 추가",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                        fieldWithPath("partnerId").type(JsonFieldType.NUMBER).description("추가할 파트너 아이디")
                )
        ));
    }



    @Test
    @Disabled
    void 사고_생성() throws Exception {
        Claim claim = GlobalTestObject.getClaim();
        when(claimService.findById(1L)).thenReturn(claim);
        ResultActions result = mockMvc.perform(
                post("/api/claim/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(GlobalTestObject.getClaimCreateRequestDto()))
        );

        result.andExpect(status().isOk()).andDo(document("claim 생성",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        GlobalTestFields.getFieldRequestClaimRequestDto()
                ),
                responseFields(
                        GlobalTestFields.getFieldResponseClaimRequestDto()
                )
                ));
    }

}