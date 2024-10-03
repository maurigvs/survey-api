package br.com.maurigvs.surveyapi.component;

import br.com.maurigvs.surveyapi.controller.ChoiceController;
import br.com.maurigvs.surveyapi.model.dto.ChoiceRequest;
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
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceComponentTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ChoiceController choiceController;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() {
        var request = MockData.ofChoiceRequest();

        given(choiceController.postChoice(1L,2L, request)).willReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey/1/question/2/choice")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), ChoiceRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() {
        given(choiceController.deleteChoice(1L,2L,3L)).willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/survey/1/question/2/choice/3")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
