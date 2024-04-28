package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.repository.UserRepository;
import br.com.maurigvs.surveyapi.service.UserService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
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
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void should_create_user() {
        var user = MockData.ofUser();
        given(userRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(userRepository.save(any())).willReturn(user);

        StepVerifier.create(userService.create(Mono.just(user)))
                .expectNext(user)
                .verifyComplete();

        verify(userRepository, times(0)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
        verifyNoMoreInteractions(userRepository);
    }
}
