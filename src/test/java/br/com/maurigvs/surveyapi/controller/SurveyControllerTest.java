package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.exception.ErrorMessageDto;
import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_survey() throws Exception {

        var request = Mock.ofSurveyDto();

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mock.toJson(request)))
                .andExpect(status().isCreated());

        verify(surveyService, times(1)).createSurvey(any());
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_return_OK_when_get_survey_list() throws Exception {

        var surveys = Mock.ofSurveyList();
        given(surveyService.findAll()).willReturn(surveys);

        mockMvc.perform(get("/survey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mock.toJson(surveys)));

        verify(surveyService, times(1)).findAll();
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_return_Bad_Request_when_MethodArgumentNotValidException_is_thrown() throws Exception {

        var request = new SurveyDto("", Mock.ofSurveyDto().questions());

        var response = new ErrorMessageDto(ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Survey title can not be blank");

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mock.toJson(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mock.toJson(response)));

        verifyNoInteractions(surveyService);
    }
}