package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
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
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {SurveyController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyControllerTest {

    @Autowired
    private SurveyController surveyController;

    @MockBean
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_survey(){
        var surveyRequestMono = Mono.just(MockData.ofSurveyRequest());
        var surveyMono = Mono.just(MockData.ofSurvey());
        given(surveyService.create(any())).willReturn(surveyMono);

        StepVerifier.create(surveyController.postSurvey(surveyRequestMono))
                .verifyComplete();

        verify(surveyService, times(1)).create(any());
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_return_OK_when_get_survey_list() throws Exception {
        var surveyFlux = Flux.just(MockData.ofSurvey());
        var surveyResponse = MockData.ofSurveyResponse();
        given(surveyService.findAll()).willReturn(surveyFlux);

        StepVerifier.create(surveyController.findAllSurveys())
                .expectNext(surveyResponse)
                .verifyComplete();

        verify(surveyService, times(1)).findAll();
        verifyNoMoreInteractions(surveyService);
    }
}