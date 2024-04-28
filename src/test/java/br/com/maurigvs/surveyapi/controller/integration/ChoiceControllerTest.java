package br.com.maurigvs.surveyapi.controller.integration;

import br.com.maurigvs.surveyapi.controller.ChoiceController;
import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
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

@SpringBootTest(properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ChoiceController choiceController;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() throws Exception {
        var choiceRequestMono = Mono.just(MockData.ofChoiceRequest());
        given(choiceController.postChoice(1L,1L,choiceRequestMono)).willReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey/1/question/1/choice")
                .contentType(MediaType.APPLICATION_JSON)
                .body(choiceRequestMono, ChoiceRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() throws Exception {
        given(choiceController.deleteChoiceById(1L,1L,2L)).willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/survey/1/question/1/choice/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
