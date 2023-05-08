package br.com.maurigvs.surveyapi.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import br.com.maurigvs.surveyapi.Mocks;
import br.com.maurigvs.surveyapi.entity.dto.SurveyDto;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService service;

    @Test
    void should_ReturnCreated_if_RequestOk_when_PostSurvey() throws Exception {
        // given
        SurveyDto dto = Mocks.surveyDto();
        String jsonPayload = Mocks.parseToJson(dto);
        // when... then
        mockMvc.perform(post("/survey")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isCreated());
    }

    @Test
    void should_ReturnBadRequest_if_RequestEmpty_when_PostSurvey() throws Exception {
        // given no content
        // when... then
        mockMvc.perform(post("/survey"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_ReturnOK_when_GetSurvey() throws Exception {
        // given
        given(service.findAll()).willReturn(Mocks.listOfSurvey());
        String jsonPayload = Mocks.parseToJson(Mocks.listOfSurvey());
        // when... then
        mockMvc.perform(get("/survey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonPayload));
    }

    @Test
    void should_ReturnOK_if_EmptyList_when_GetSurvey() throws Exception {
        // given
        given(service.findAll()).willReturn(Collections.emptyList());
        String jsonPayload = Mocks.parseToJson(Collections.emptyList());
        // when... then
        mockMvc.perform(get("/survey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonPayload));
    }
}