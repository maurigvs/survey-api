package br.com.maurigvs.surveyapi.integration;

import br.com.maurigvs.surveyapi.controller.AnswerController;
import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.Disabled;
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

@Disabled
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerIT {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private AnswerController controller;

    @Test
    void should_return_Created_when_post_answer() {
        AnswerRequest answerRequest = MockData.ofAnswerRequest();
        var answerRequestMono = Mono.just(answerRequest);
        given(controller.postAnswer(1L, answerRequest)).willReturn(Mono.empty());

        webClient.post()
                .uri("/survey/1/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(answerRequestMono, AnswerRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_Ok_when_get_answers() {
        var answerResponse = MockData.ofAnswerResponse();
        var answerResponseFlux = Flux.just(MockData.ofAnswerResponse());
        given(controller.getAnswerList()).willReturn(answerResponseFlux);

        webClient.get()
                .uri("/survey/answer")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBodyList(AnswerResponse.class).contains(answerResponse);
    }
}