package br.com.maurigvs.surveyapi.mapper;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocalDateTimeMapperTest {

    @Test
    void should_return_String_given_an_LocalDateTime() {
        var localDateTime = LocalDateTime.of(2024,2,17,16,44);
        var expected = "17/02/2024 16:44";

        var result = new LocalDateTimeMapper().apply(localDateTime);

        assertEquals(expected, result);
    }
}
