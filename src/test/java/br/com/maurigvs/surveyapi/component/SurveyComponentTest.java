package br.com.maurigvs.surveyapi.component;

import br.com.maurigvs.surveyapi.controller.SurveyController;
import br.com.maurigvs.surveyapi.controller.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.controller.dto.SurveyResponse;
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

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurveyRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurveyResponse;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyComponentTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SurveyController surveyController;

    @Test
    void should_return_Created_when_add_survey() {
        SurveyRequest request = mockOfSurveyRequest();
        when(surveyController.postSurvey(request)).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), SurveyRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_OK_when_find_all_surveys() {
        SurveyResponse response = mockOfSurveyResponse();
        when(surveyController.findAllSurveys()).thenReturn(Flux.just(response));

        webTestClient.get()
                .uri("/survey")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBodyList(SurveyResponse.class).contains(response);
    }
}
