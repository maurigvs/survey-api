package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewQuestionRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfQuestion;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurvey;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    @Mock
    private SurveyService surveyService;

    @Captor
    private ArgumentCaptor<Question> questionCaptor;

    @Test
    void should_return_Created_when_add_question_to_existing_survey() {
        QuestionRequest request = mockOfNewQuestionRequest();
        Survey survey = mockOfSurvey();
        given(surveyService.findById(1L)).willReturn(Mono.just(survey));
        given(questionService.create(any(Question.class))).willReturn(Mono.empty());

        StepVerifier.create(questionController.postQuestion(1L, request))
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() {
        Question question = mockOfQuestion();
        given(surveyService.findQuestionInSurvey(1L, 2L)).willReturn(Mono.just(question));
        given(questionService.delete(any(Question.class))).willReturn(Mono.empty());

        StepVerifier.create(questionController.deleteQuestionById(1L, 2L))
                .verifyComplete();

        verify(questionService).delete(questionCaptor.capture());
        assertSame(question, questionCaptor.getValue());
    }
}
