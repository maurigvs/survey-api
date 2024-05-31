package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AnswerMapperTest {

    @Test
    void toEntity() {
    }

    @Test
    void toResponse() {
        var answer = MockData.ofAnswer();

        var response = AnswerMapper.toResponse(answer);

        assertNotNull(response);
    }
}