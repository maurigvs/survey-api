package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.service.AnswerService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_answer() throws Exception {
        var request = Mock.ofAnswerRequest();
        given(surveyService.findById(anyLong())).willReturn(Mock.ofSurvey());

        mockMvc.perform(post("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mock.ofJson(request)))
                .andExpect(status().isCreated());

        verify(surveyService, times(1)).findById(request.surveyId());
        verify(answerService, times(1)).createAnswer(any());
        verifyNoMoreInteractions(surveyService, answerService);
    }

    @Test
    void should_return_Ok_when_get_answers() throws Exception {
        var response = Mock.ofAnswerResponse();
        given(answerService.findAll()).willReturn(List.of(Mock.ofAnswer()));

        mockMvc.perform(get("/answer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(Mock.ofJson(List.of(response))));
    }
}