package com.Insurance.hm.board;

import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.board.service.BoardService;
import com.Insurance.hm.global.domain.file.FileService;
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

import static com.Insurance.hm.util.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = BoardController.class)
class BoardControllerTest {

    @Autowired
    WebApplicationContext ctx;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    BoardService boardService;
    @MockBean
    FileService fileService;


    @BeforeEach
    public void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }

    @Test
    void ?????????_????????????_??????() throws Exception{
        BoardCreateDto boardCreateDto = GlobalTestObject.getBoardCreateDto();
        when(boardService.findById(1L)).thenReturn(GlobalTestObject.getBoard());
        ResultActions result = mockMvc.perform(get("/api/board/{id}",1L));
        result.andExpect(status().isOk()).andDo(document("Board-findById",
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(GlobalTestFields.getFieldResponseBoardDetailDto())
        ));
    }

    @Test
    @Disabled
    void ?????????_????????????() throws Exception{
        BoardCreateDto boardCreateDto = GlobalTestObject.getBoardCreateDto();
        when(boardService.findById(1L)).thenReturn(GlobalTestObject.getBoard());
        ResultActions result = mockMvc.perform(post("/api/board/{id}",1L)
                .content(objectMapper.writeValueAsString(boardCreateDto))
                .contentType(MediaType.APPLICATION_JSON)
                );
        result.andExpect(status().isFound()).andDo(document("Board-update",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(GlobalTestFields.getFieldRequestBoardCreateDto())
        ));
    }

    @Test
    @Disabled
    void ?????????_??????() throws Exception{
        BoardCreateDto boardCreateDto = GlobalTestObject.getBoardCreateDto();
        when(boardService.findById(1L)).thenReturn(GlobalTestObject.getBoard());

        ResultActions result = mockMvc.perform(post("/api/board/write").contentType(MediaType.MULTIPART_FORM_DATA_VALUE));

        result.andExpect(status().isFound()).andDo(document("Board-write",
                ApiDocumentUtils.getDocumentRequest(),
                requestFields(GlobalTestFields.getFieldRequestBoardCreateDto())
        ));
    }


    @Test
    void ?????????_??????() throws Exception {

        //when
        when(boardService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(delete("/api/board/{id}", 1L));

        //then
        result.andExpect(status().isOk()).andDo(document("Board-delete",
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP ??????"),
                        fieldWithPath("result").type(JsonFieldType.STRING).description("?????? ??????"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("?????????"),
                        fieldWithPath("data").type(JsonFieldType.NUMBER).description("????????? ????????? ?????????")
                )
        ));

    }


}