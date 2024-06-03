package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
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
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {SurveyController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyControllerTest {

    @Autowired
    private SurveyController controller;

    @MockBean
    private AggregatorService service;

    @Test
    void should_return_Created_when_post_survey(){
        var surveyRequest = MockData.ofSurveyRequest();
        var surveyResponse = MockData.ofSurveyResponse();
        given(service.createSurvey(any())).willReturn(Mono.just(surveyResponse));

        StepVerifier.create(controller.postSurvey(surveyRequest))
                .expectNext(surveyResponse)
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_get_survey_list() {
        var surveyResponse = MockData.ofSurveyResponse();
        given(service.findAllSurveys()).willReturn(Flux.just(surveyResponse));

        StepVerifier.create(controller.getSurveyList())
                .expectNext(surveyResponse)
                .verifyComplete();
    }
}