package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.model.Survey;
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

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurvey;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurveyRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurveyResponse;
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
    void should_return_Created_when_post_survey() {
        SurveyRequest request = mockOfSurveyRequest();
        given(surveyService.create(any(Survey.class))).willReturn(Mono.empty());

        StepVerifier.create(surveyController.postSurvey(request))
                .expectNext()
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_get_survey_list() {
        Survey survey = mockOfSurvey();
        SurveyResponse response = mockOfSurveyResponse();
        given(surveyService.findAll()).willReturn(Flux.just(survey));

        StepVerifier.create(surveyController.findAllSurveys())
                .expectNext(response)
                .verifyComplete();
    }
}