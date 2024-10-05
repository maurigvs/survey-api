package br.com.maurigvs.surveyapi.component;

import br.com.maurigvs.surveyapi.controller.AnswerController;
import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.dto.ErrorResponse;
import br.com.maurigvs.surveyapi.dto.ItemRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswerRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswerResponse;
import static br.com.maurigvs.surveyapi.mocks.MockData.ofJson;
import static org.mockito.Mockito.when;

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
        AnswerRequest request = mockOfAnswerRequest();
        when(answerController.postAnswer(1L, request)).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/survey/1/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AnswerResponse.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getBadRequestPossibilities")
    void should_return_Bad_Request_when_post_answer_is_malformed(AnswerRequest request, String errorMessage) {
        String requestJson = ofJson(request);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse expected = new ErrorResponse(httpStatus, errorMessage);

        webTestClient.post()
                .uri("/survey/1/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestJson)
                .exchange()
                .expectStatus().isEqualTo(httpStatus.value())
                .expectBody(ErrorResponse.class).isEqualTo(expected);
    }

    private static Stream<Arguments> getBadRequestPossibilities() {
        return Stream.of(
                Arguments.of(new AnswerRequest(0L, null),
                        "The field [answers] must not be empty"),
                Arguments.of(new AnswerRequest(0L, List.of(new ItemRequest(null, 1L))),
                        "The field [answers[0].questionId] must not be null"),
                Arguments.of(new AnswerRequest(0L, List.of(new ItemRequest(1L, null))),
                        "The field [answers[0].choiceId] must not be null")
        );
    }

    @Test
    void should_return_PreconditionFailed_when_post_answer_to_non_existing_survey() {
        AnswerRequest request = mockOfAnswerRequest();
        HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;
        ErrorResponse expected = new ErrorResponse(httpStatus, "Survey not found");

        when(answerController.postAnswer(1L, mockOfAnswerRequest()))
                .thenThrow(new NoSuchElementException("Survey not found"));

        webTestClient.post()
                .uri("/survey/1/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AnswerRequest.class)
                .exchange()
                .expectStatus().isEqualTo(httpStatus.value())
                .expectBody(ErrorResponse.class).isEqualTo(expected);
    }

    @Test
    void should_return_InternalServerError_when_post_answer_throws_exception() {
        AnswerRequest request = mockOfAnswerRequest();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse expected = new ErrorResponse(httpStatus, "Something went wrong");

        when(answerController.postAnswer(1L, mockOfAnswerRequest()))
                .thenThrow(new RuntimeException("Something went wrong"));

        webTestClient.post()
                .uri("/survey/1/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), AnswerRequest.class)
                .exchange()
                .expectStatus().isEqualTo(httpStatus.value())
                .expectBody(ErrorResponse.class).isEqualTo(expected);
    }

    @Test
    void should_return_Ok_when_get_answers() {
        AnswerResponse response = mockOfAnswerResponse();
        when(answerController.findAllAnswers()).thenReturn(Flux.just(response));

        webTestClient.get()
                .uri("/survey/answer")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBodyList(AnswerResponse.class).contains(response);
    }
}
