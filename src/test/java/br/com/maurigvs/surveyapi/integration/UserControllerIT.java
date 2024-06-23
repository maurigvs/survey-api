package br.com.maurigvs.surveyapi.integration;

import br.com.maurigvs.surveyapi.controller.UserController;
import br.com.maurigvs.surveyapi.dto.requests.UserRequest;
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
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;

@Disabled
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
@AutoConfigureWebTestClient
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserController userController;

    @Test
    void should_return_Created_when_post_user() {
        var userRequestMono = Mono.just(MockData.ofUserRequest());
        var userResponseMono = Mono.just(MockData.ofUserCreatedResponse(LocalDateTime.now()));
        given(userController.postUser(userRequestMono)).willReturn(userResponseMono);

        webTestClient.post()
                .uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRequestMono, UserRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().equals(userResponseMono);
    }
}