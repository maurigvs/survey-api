package br.com.maurigvs.surveyapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.maurigvs.surveyapi.CustomMocks;
import br.com.maurigvs.surveyapi.exception.StandardError;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.service.SurveyService;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService surveyService;

    @Test
    void should_Return404_when_PostSurveyWithoutBody() throws Exception {
        mockMvc.perform(post("/survey")).andExpect(status().isBadRequest());
    }

    @Test
    void should_ReturnCreated_when_PostSurveyWithData() throws Exception {
        SurveyDto surveyDto = CustomMocks.mockSurveyDto();
        String surveyAsJson = CustomMocks.parseToJson(surveyDto);

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(surveyAsJson))
                .andExpect(status().isCreated());

        verify(surveyService, times(1)).createSurvey(any(SurveyDto.class));
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_ReturnBadRequest_when_PostSurveyWithMissingTitle() throws Exception {
        SurveyDto surveyDto = CustomMocks.mockSurveyDto();
        surveyDto.setSurvey("");
        String surveyAsJson = CustomMocks.parseToJson(surveyDto);

        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.getReasonPhrase(),"Survey title can not be blank");
        String errorAsJson = CustomMocks.parseToJson(error);

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(surveyAsJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(errorAsJson));

        verifyNoInteractions(surveyService);
    }
}