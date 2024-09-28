package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private SurveyService surveyService;

    @Mock
    private QuestionService questionService;

    @Test
    void should_return_Created_when_add_question_to_existing_survey() {
        var survey = MockData.ofSurvey();
        var question = MockData.ofQuestion();
        var request = MockData.ofQuestionRequest();
        var response = MockData.ofQuestionResponse();

        given(surveyService.findById(1L)).willReturn(Mono.just(survey));
        given(questionService.save(any(Question.class))).willReturn(Mono.just(question));

        StepVerifier.create(questionController.postQuestion(1L, request))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() {
        var question = MockData.ofQuestion();

        given(surveyService.findQuestionInSurvey(1L ,2L)).willReturn(Mono.just(question));
        given(questionService.delete(question)).willReturn(Mono.empty());

        StepVerifier.create(questionController.deleteQuestion(1L,2L))
                .verifyComplete();
    }
}
