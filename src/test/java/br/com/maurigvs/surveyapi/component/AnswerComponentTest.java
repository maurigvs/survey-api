package br.com.maurigvs.surveyapi.component;

import br.com.maurigvs.surveyapi.controller.AnswerController;
import br.com.maurigvs.surveyapi.model.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.model.dto.AnswerResponse;
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

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerComponentTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AnswerController answerController;

    @Test
    void should_return_Created_when_post_answer() {
        var request = MockData.ofAnswerRequest();

        given(answerController.postAnswer(1L, request)).willReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey/1/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AnswerRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_Ok_when_get_answers() {
        var response = MockData.ofAnswerResponse();

        given(answerController.getAnswerList()).willReturn(Flux.just(response));

        webTestClient.get()
                .uri("/survey/answer")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBodyList(AnswerResponse.class).contains(response);
    }
}