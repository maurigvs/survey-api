package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {QuestionController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionControllerTest {

    @Autowired
    private QuestionController questionController;

    @MockBean
    private SurveyService surveyService;

    @MockBean
    private QuestionService questionService;

    @Test
    void should_return_Created_when_add_question_to_existing_survey() throws Exception {
        var surveyMono = Mono.just(MockData.ofSurvey());
        var questionMono = Mono.just(MockData.ofQuestion());
        var questionRequestMono = Mono.just(MockData.ofQuestionRequest());
        given(surveyService.findById(1L)).willReturn(surveyMono);
        given(questionService.create(any())).willReturn(questionMono);
        
        StepVerifier.create(questionController.postQuestion(1L, questionRequestMono))
                .verifyComplete();

        verify(surveyService, times(1)).findById(1L);
        verify(questionService, times(1)).create(any(Mono.class));
        verifyNoMoreInteractions(surveyService, questionService);
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() throws Exception {
        given(questionService.deleteById(anyLong(), anyLong())).willReturn(Mono.empty());

        StepVerifier.create(questionController.deleteQuestionById(1L, 2L))
                .verifyComplete();

        verify(questionService, times(1)).deleteById(2L,1L);
        verifyNoMoreInteractions(questionService);
        verifyNoInteractions(surveyService);
    }
}