package com.Insurance.hm.compensation;

import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.compensation.dto.CompensationChangeStatusRequestDto;
import com.Insurance.hm.compensation.service.CompensationService;
import com.Insurance.hm.util.ApiDocumentUtils;
import com.Insurance.hm.util.GlobalTestFields;
import com.Insurance.hm.util.GlobalTestObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CompensationController.class)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class CompensationControllerTest {

    @MockBean
    CompensationService compensationService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext ctx;
    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    private void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }


    @Test
    void 보상_아이디로_조회() throws Exception {
        when(compensationService.findById(1L)).thenReturn(GlobalTestObject.getCompensation());

        ResultActions result = mockMvc.perform(get("/api/compensation/{id}", 1L));
        result.andExpect(status().isOk()).andDo(document("compensation-findById",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseCompensationDetailDto()
                )
        ));
    }

    @Test
    void 보상_상태_변경() throws Exception{
        CompensationChangeStatusRequestDto changeStatusRequestDto = new CompensationChangeStatusRequestDto();
        changeStatusRequestDto.setStatus(CompensationStatus.보상완료);
        when(compensationService.changeStatus(1L,changeStatusRequestDto)).thenReturn(1L);
        ResultActions result = mockMvc.perform(post("/api/compensation/{id}/status", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(changeStatusRequestDto))
        );

        result.andExpect(status().isFound()).andDo(document("compensation-status",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                    fieldWithPath("status").type(JsonFieldType.STRING).description("보상 상태")
                )
        ));
    }

}