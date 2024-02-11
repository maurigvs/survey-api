package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @MockBean
    private QuestionService questionService;

    @Test
    void should_return_OK_when_add_question_to_existing_survey() throws Exception {
        var survey = Mock.ofSurvey();
        var question = Mock.ofQuestionRequest1();
        given(surveyService.findById(anyInt())).willReturn(survey);

        mockMvc.perform(put("/survey/1/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mock.ofJson(question)))
                .andExpect(status().isOk());

        verify(surveyService, times(1)).findById(1);
        verify(questionService, times(1)).createQuestion(any(Question.class));
        verifyNoMoreInteractions(surveyService, questionService);
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() throws Exception {

        mockMvc.perform(delete("/survey/1/question/2"))
                .andExpect(status().isOk());

        verify(questionService, times(1)).deleteById(2,1);
        verifyNoMoreInteractions(questionService);
        verifyNoInteractions(surveyService);
    }
}