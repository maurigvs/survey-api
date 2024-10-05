package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.controller.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.service.AnswerService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswer;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswerRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswerResponse;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurvey;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerTest {

    @InjectMocks
    private AnswerController answerController;

    @Mock
    private AnswerService answerService;

    @Mock
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_answer() {
        AnswerRequest request = mockOfAnswerRequest();
        Survey survey = mockOfSurvey();
        when(surveyService.findById(1L)).thenReturn(Mono.just(survey));
        when(answerService.create(any())).thenReturn(Mono.empty());

        StepVerifier.create(answerController.postAnswer(1L, request))
                .verifyComplete();
    }

    @Test
    void should_return_Ok_when_get_answers() {
        Answer answer = mockOfAnswer();
        AnswerResponse response = mockOfAnswerResponse();

        when(answerService.findAll()).thenReturn(Flux.just(answer));

        StepVerifier.create(answerController.findAllAnswers())
                .expectNext(response)
                .verifyComplete();
    }
}
