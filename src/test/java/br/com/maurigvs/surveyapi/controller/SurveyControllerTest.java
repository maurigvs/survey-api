package br.com.maurigvs.surveyapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.maurigvs.surveyapi.controller.SurveyController.SurveyRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService service;

    @Test
    void should_ReturnStatusCreated_when_PostSurvey() throws Exception {

        SurveyRequest survey = new SurveyRequest("My Survey");
        String request = parseToJson(survey);

        mockMvc.perform(post("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(request))
                .andExpect(status().isCreated());
    }

    private String parseToJson(Object object) throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}