package com.Insurance.hm.client;

import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.dto.ClientCreateRequestDto;
import com.Insurance.hm.client.service.ClientService;
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

import static com.Insurance.hm.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext ctx;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ClientService clientService;

    @BeforeEach
    public void setup(RestDocumentationContextProvider documentationContextProvider){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .apply(documentationConfiguration(documentationContextProvider))
                .alwaysDo(print())
                .build();
    }


    @Test
    public void Client_아이디로_조회() throws Exception{
        //given
        Client client = GlobalTestObject.getClient();
        client.getContractList().add(GlobalTestObject.getContract());
        client.getAccidentHistoryList().add(GlobalTestObject.getAccidentHistory());
        //when
        when(clientService.find(1L)).thenReturn(client);
        //then
        ResultActions result = mockMvc.perform(get("/api/client/{id}",1L));

        result.andExpect(status().isOk())
                .andDo(document("client-findById",
                    ApiDocumentUtils.getDocumentResponse(),
                    responseFields(
                            GlobalTestFields.getFieldResponseClientDetailDto()
                    )
                ));
    }

    @Test
    void Client_삭제_API() throws Exception{
        //given
        //when
        when(clientService.deleteById(1L)).thenReturn(1L);
        ResultActions result = mockMvc.perform(delete("/api/client/{id}", 1L));
        //then
        result.andExpect(status().isOk())
                .andDo(document("client-delete",
                        getDocumentResponse(),
                        responseFields(
                                GlobalTestFields.getFieldResponseDelete("삭제된 Client 아이디")
                        )
                        )
                );
    }

    @Test
    public void Client_생성() throws Exception{
        //given
        ClientCreateRequestDto clientCreateRequestDto = GlobalTestObject.getClientCreateRequestDto();
        //when
        when(clientService.find(1L)).thenReturn(clientCreateRequestDto.toEntity());
        //then
        ResultActions result = mockMvc.perform(post("/api/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientCreateRequestDto)));

        result.andExpect(status().isFound())
                .andDo(document("client-create",
                    ApiDocumentUtils.getDocumentRequest(),
                    requestFields(
                            GlobalTestFields.getFieldRequestClientSignRequestDto()
                    )
                ));
    }

    @Test
    void 고객_부가정보_추가(){

    }

}