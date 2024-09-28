package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyControllerTest {

    @InjectMocks
    private SurveyController surveyController;

    @Mock
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_survey(){
        var survey = MockData.ofSurvey();
        var request = MockData.ofSurveyRequest();
        var response = MockData.ofSurveyResponse();

        given(surveyService.save(any())).willReturn(Mono.just(survey));

        StepVerifier.create(surveyController.postSurvey(request))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_get_survey_list() {
        var survey = MockData.ofSurvey();
        var response = MockData.ofSurveyResponse();

        given(surveyService.findAll()).willReturn(Flux.just(survey));

        StepVerifier.create(surveyController.getSurveyList())
                .expectNext(response)
                .verifyComplete();
    }
}