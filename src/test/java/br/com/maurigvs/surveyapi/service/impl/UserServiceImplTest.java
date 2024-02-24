package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.UserAlreadyExistsException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        var login = "john.snow";
        given(repository.existsByEmail(anyString())).willReturn(false);
        given(repository.save(any())).willReturn(user);

        var result = service.create(user);

        verify(repository, times(1)).existsByEmail(user.getEmail());
        verify(repository, times(1)).save(userCaptor.capture());
        verifyNoMoreInteractions(repository);
        var userCreated = userCaptor.getValue();
        assertEquals(login, userCreated.getLogin());
        assertSame(userCreated, result);
    }

    @Test
    void should_throw_exception_when_user_with_same_email_already_exists() {
        var messageExpected = "User already exists with same email";
        var user = MockData.ofUserWithLogin();
        given(repository.existsByEmail(anyString())).willReturn(true);

        var exception = assertThrows(UserAlreadyExistsException.class, () -> service.create(user));
        assertEquals(messageExpected, exception.getMessage());

        verify(repository, times(1)).existsByEmail(user.getEmail());
        verifyNoMoreInteractions(repository);
    }
}
