package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserCreatedResponseMapperTest {

    @Test
    void should_return_UserCreatedResponse_given_an_User() {
        var user = MockData.ofUser();
        var createdAt = new LocalDateTimeMapper().apply(user.getCreatedAt());

        var result = new UserCreatedResponseMapper().apply(user);

        assertEquals(user.getLogin(), result.login());
        assertEquals(createdAt, result.createdAt());
    }
}
