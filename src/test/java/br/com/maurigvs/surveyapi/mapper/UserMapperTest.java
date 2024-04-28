package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserMapperTest {

    @Test
    void should_return_User_given_an_UserRequest() {
        var request = MockData.ofUserRequest();
        var createdAt = LocalDateTime.now();

        var result = new UserMapper(createdAt).apply(request);

        assertNull(result.getId());
        assertEquals(request.name(), result.getName());
        assertEquals(request.email(), result.getEmail());
        assertEquals("john.snow", result.getLogin());
        assertEquals(request.password(), result.getPassword());
        assertEquals(createdAt, result.getCreatedAt());
    }
}