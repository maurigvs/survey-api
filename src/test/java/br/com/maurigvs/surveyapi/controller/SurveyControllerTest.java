package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mock.Mocks;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService service;

    @Test
    void should_ReturnCreated_when_PostSurvey() throws Exception {
        SurveyRequest request = Mocks.getSurveyRequestValid();
        String jsonContent = Mocks.parseToJson(request);
        mockMvc.perform(post("/api/survey")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
                .andExpect(status().isCreated());
    }

    @Test
    void should_ReturnCreated_when_PostAnswers() throws Exception{
        AnswerRequest request = Mocks.getAnswerRequestValid();
        String jsonContent = Mocks.parseToJson(request);
        mockMvc.perform(post("/api/answer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
                .andExpect(status().isCreated());
    }

    @Test
    void should_ReturnBadRequest_when_PostSurvey_if_EmptyTitle() throws Exception {
        String jsonContent = Mocks.getSurveyWithoutTitle();
        mockMvc.perform(post("/api/survey")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_ReturnOK_when_GetSurveys() throws Exception {
        // given
        List<Survey> result = Mocks.getSurveyList();
        String content = Mocks.parseToJson(result);
        given(service.listSurveys()).willReturn(result);
        // when... then
        mockMvc.perform(get("/api/survey"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content))
                .andExpect(status().isOk());
    }

    @Test
    void should_ReturnOK_when_GetSurveys_if_EmptyList() throws Exception {
        // given
        List<Survey> result = Collections.emptyList();
        String content = Mocks.parseToJson(result);
        given(service.listSurveys()).willReturn(result);
        // when... then
        mockMvc.perform(get("/api/survey"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content))
                .andExpect(status().isOk());
    }
}