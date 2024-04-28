package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.User;
import br.com.maurigvs.surveyapi.repository.UserRepository;
import br.com.maurigvs.surveyapi.service.UserService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {UserServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void should_create_user() {
        var user = MockData.ofUser();
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(any())).willReturn(user);

        StepVerifier.create(service.create(Mono.just(user)))
                .expectNext(user)
                .verifyComplete();

        verify(repository, times(0)).findByEmail(user.getEmail());
        verify(repository, times(1)).save(user);
        verifyNoMoreInteractions(repository);
    }
}
