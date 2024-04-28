package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.service.AnswerService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {AnswerController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerTest {

    @Autowired
    private AnswerController answerController;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_answer() throws Exception {
        var answerMono = Mono.just(MockData.ofAnswer());
        var surveyMono = Mono.just(MockData.ofSurvey());
        var answerRequestMono = Mono.just(MockData.ofAnswerRequest());
        given(surveyService.findById(1L)).willReturn(surveyMono);
        given(answerService.create(any())).willReturn(answerMono);

        StepVerifier.create(answerController.postAnswer(1L, answerRequestMono))
                .verifyComplete();
    }

    @Test
    void should_return_error_when_post_answer() {
        var answerRequestMono = Mono.just(MockData.ofAnswerRequest());
        given(surveyService.findById(1L)).willReturn(Mono.error(new SurveyNotFoundException(1L)));

        StepVerifier.create(answerController.postAnswer(1L, answerRequestMono))
                .expectErrorMatches(throwable ->
                        throwable instanceof SurveyNotFoundException &&
                        throwable.getMessage().equals("Survey not found by Id 1"))
                .verify();

        verifyNoInteractions(answerService);
    }

    @Test
    void should_return_Ok_when_get_answers() {
        var answerFlux = Flux.just(MockData.ofAnswer());
        var answerResponse = MockData.ofAnswerResponse();
        given(answerService.findAll()).willReturn(answerFlux);

        StepVerifier.create(answerController.findAllAnswers())
                .expectNext(answerResponse)
                .verifyComplete();

        verify(answerService, times(1)).findAll();
        verifyNoMoreInteractions(answerService);
    }
}