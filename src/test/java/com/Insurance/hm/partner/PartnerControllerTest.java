package com.Insurance.hm.partner;

import com.Insurance.hm.partner.domain.entity.PartnerCategory;
import com.Insurance.hm.partner.dto.PartnerCreateRequestDto;
import com.Insurance.hm.partner.service.PartnerService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = PartnerController.class)
class PartnerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext ctx;
    @MockBean
    PartnerService partnerService;


    @BeforeEach
    private void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }


    @Test
    void 파트너_아이디로_조회() throws Exception{
        when(partnerService.findById(1L)).thenReturn(GlobalTestObject.getPartner());
        ResultActions result = mockMvc.perform(get("/api/partner/{id}",1L)
        );
        
        result.andExpect(status().isOk()).andDo(document("partner-findById",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponsePartnerDetailDto()
                )
        ));
    }

    @Test
    void 파트너_아이디로_삭제() throws Exception{
        when(partnerService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(delete("/api/partner/{id}",1L));
        result.andExpect(status().isOk()).andDo(document("partner-delete",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseDelete("삭제된 파트너 아이디")
        )));
    }
    @Test
    void 파트너_생성() throws Exception{
        when(partnerService.findById(1L)).thenReturn(GlobalTestObject.getPartner());
        ResultActions result = mockMvc.perform(post("/api/partner/create")
                .content(objectMapper.writeValueAsString(GlobalTestObject.getPartnerCreateRequestDto()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isFound()).andDo(document("partner-create",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("파트너 이름"),
                        fieldWithPath("address").type(JsonFieldType.STRING).description("파트너 주소"),
                        fieldWithPath("contactNumber").type(JsonFieldType.STRING).description("파트너 전화번호"),
                        fieldWithPath("category").type(JsonFieldType.STRING).description("파트너 카테고리"),
                        fieldWithPath("employeeId").type(JsonFieldType.NUMBER).description("파트너 담당 직원 아이디")
                )
        ));
    }



}