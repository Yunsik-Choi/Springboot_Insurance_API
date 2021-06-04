package com.Insurance.hm.AccidentHistory;

import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryCreateRequestDto;
import com.Insurance.hm.AccidentHistory.service.AccidentHistoryService;
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
@WebMvcTest(controllers = AccidentHistoryController.class)
class AccidentHistoryControllerTest {

    @Autowired
    WebApplicationContext ctx;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccidentHistoryService accidentHistoryService;

    @BeforeEach
    public void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }

    @Test
    public void 사고이력_생성() throws Exception{
        //given
        AccidentHistoryCreateRequestDto requestDto = GlobalTestObject.getAccidentHistoryCreateRequestDto();
        when(accidentHistoryService.findById(1L)).thenReturn(GlobalTestObject.getAccidentHistory());

        //when
        ResultActions result = mockMvc.perform(
                post("/api/history/create")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isFound()).andDo(document("AccidentHistory-create",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(
                        GlobalTestFields.getFieldRequestAccidentHistoryCreateDto()
                )
        ));

    }

    @Test
    public void 사고이력_아이디로_조회() throws Exception{
        //given
        when(accidentHistoryService.findById(1L)).thenReturn(GlobalTestObject.getAccidentHistory());

        //when
        ResultActions result = mockMvc.perform(
                get("/api/history/{id}",1L)
        );

        //then
        result.andExpect(status().isOk()).andDo(document("AccidentHistory-findById",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseAccidentHistoryDetailDto()
                )
        ));

    }
    @Test
    public void 사고이력_아이디로_삭제() throws Exception{
        //given
        when(accidentHistoryService.findById(1L)).thenReturn(GlobalTestObject.getAccidentHistory());

        //when
        ResultActions result = mockMvc.perform(
                delete("/api/history/{id}",1L)
        );

        //then
        result.andExpect(status().isOk()).andDo(document("AccidentHistory-delete",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        GlobalTestFields.getFieldResponseDelete("삭제된 AccidentHistory 아이디")
                )
        ));

    }

}