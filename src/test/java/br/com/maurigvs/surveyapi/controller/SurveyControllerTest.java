package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mock.Mocks;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService surveyService;

    @Test
    void should_ReturnStatusCreated_when_PostSurvey() throws Exception {
        SurveyRequest request = Mocks.getSurveyRequestValid();
        String jsonContent = Mocks.parseToJson(request);
        mockMvc.perform(post("/api/survey")
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
}