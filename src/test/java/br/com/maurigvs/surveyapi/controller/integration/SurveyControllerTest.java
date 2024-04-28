package br.com.maurigvs.surveyapi.controller.integration;

import br.com.maurigvs.surveyapi.controller.SurveyController;
import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@SpringBootTest(properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SurveyController surveyController;

    @Test
    void should_return_Created_when_post_survey() {
        var surveyRequestMono = Mono.just(MockData.ofSurveyRequest());
        given(surveyController.postSurvey(surveyRequestMono)).willReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey")
                .contentType(MediaType.APPLICATION_JSON)
                .body(surveyRequestMono, SurveyRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_OK_when_get_survey_list() {
        var surveyResponseFlux = Flux.just(MockData.ofSurveyResponse());
        given(surveyController.findAllSurveys()).willReturn(surveyResponseFlux);

        webTestClient.get()
                .uri("/survey")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBody().equals(surveyResponseFlux);
    }
}