package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.DataMock;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void should_return_Created_when_add_question_to_existing_survey() throws Exception {
        var survey = DataMock.ofSurvey();
        var questionRequest = DataMock.ofQuestionRequest1();
        given(surveyService.findById(anyLong())).willReturn(survey);

        mockMvc.perform(post("/survey/1/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataMock.ofJson(questionRequest)))
                .andExpect(status().isCreated());

        verify(surveyService, times(1)).findById(1L);
        verify(questionService, times(1)).create(any(Question.class));
        verifyNoMoreInteractions(surveyService, questionService);
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() throws Exception {

        mockMvc.perform(delete("/survey/1/question/2"))
                .andExpect(status().isOk());

        verify(questionService, times(1)).deleteById(2L,1L);
        verifyNoMoreInteractions(questionService);
        verifyNoInteractions(surveyService);
    }
}