package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.entity.Answer;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerTest {

    @InjectMocks
    private AnswerController answerController;

    @Mock
    private SurveyService surveyService;

    @Mock
    private AnswerService answerService;

    @Test
    void should_return_Created_when_post_answer() {
        var survey = MockData.ofSurvey();
        var answer = MockData.ofAnswer();
        var request = MockData.ofAnswerRequest();
        var response = MockData.ofAnswerResponse();

        given(surveyService.findById(anyLong())).willReturn(Mono.just(survey));
        given(answerService.save(any(Answer.class))).willReturn(Mono.just(answer));

        StepVerifier.create(answerController.postAnswer(1L, request))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void should_return_Ok_when_get_answers() {
        var answer = MockData.ofAnswer();
        var response = MockData.ofAnswerResponse();

        given(answerService.findAll()).willReturn(Flux.just(answer));

        StepVerifier.create(answerController.getAnswerList())
                .expectNext(response)
                .verifyComplete();

        verifyNoInteractions(surveyService);
    }
}
