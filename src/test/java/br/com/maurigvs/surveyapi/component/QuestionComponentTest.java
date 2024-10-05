package br.com.maurigvs.surveyapi.component;

import br.com.maurigvs.surveyapi.controller.QuestionController;
import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewQuestionRequest;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionComponentTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private QuestionController questionController;

    @Test
    void should_return_Created_when_add_question_to_existing_survey() {
        QuestionRequest request = mockOfNewQuestionRequest();
        given(questionController.postQuestion(1L, request)).willReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey/1/question")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), QuestionRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() {
        given(questionController.deleteQuestionById(1L, 2L)).willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/survey/1/question/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
