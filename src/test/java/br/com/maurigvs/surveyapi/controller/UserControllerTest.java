package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.service.UserService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {UserController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void should_return_Created_when_post_user() throws Exception {
        var userRequestMono = Mono.just(MockData.ofUserRequest());
        var userMono = Mono.just(MockData.ofUser());
        var userCreatedResponse = MockData.ofUserCreatedResponse(LocalDateTime.now());
        given(userService.create(any())).willReturn(userMono);

        StepVerifier.create(userController.postUser(userRequestMono))
                .expectNext(userCreatedResponse)
                .verifyComplete();

        verify(userService, times(1)).create(any(Mono.class));
        verifyNoMoreInteractions(userService);
    }
}