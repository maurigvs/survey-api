package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.exception.NotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.service.AggregatorService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {AnswerController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerTest {

    @Autowired
    private AnswerController controller;

    @MockBean
    private AggregatorService service;

    @Test
    void should_return_Created_when_post_answer() {
        var answerRequest = MockData.ofAnswerRequest();
        var answerResponse = MockData.ofAnswerResponse();
        given(service.createAnswer(anyLong(), any())).willReturn(Mono.just(answerResponse));

        StepVerifier.create(controller.postAnswer(1L, answerRequest))
                .expectNext(answerResponse)
                .verifyComplete();
    }

    @Test
    void should_return_error_when_post_answer() {
        var answerRequest = MockData.ofAnswerRequest();
        given(service.createAnswer(anyLong(), any())).willReturn(Mono.error(new NotFoundException(Survey.class, 1L)));

        StepVerifier.create(controller.postAnswer(1L, answerRequest))
                .expectErrorMatches(throwable ->
                        throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("Survey not found by Id 1"))
                .verify();
    }

    @Test
    void should_return_Ok_when_get_answers() {
        var answerResponse = MockData.ofAnswerResponse();
        given(service.findAllAnswers()).willReturn(Flux.just(answerResponse));

        StepVerifier.create(controller.getAnswerList())
                .expectNext(answerResponse)
                .verifyComplete();

        verify(service, times(1)).findAllAnswers();
        verifyNoMoreInteractions(service);
    }
}