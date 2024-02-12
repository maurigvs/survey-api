package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.DataMock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerResponseMapperTest {

    @Test
    void should_return_SurveyResponse_given_an_Answer() {
        var answer = DataMock.ofAnswer();
        var surveyResponse = DataMock.ofSurveyResponseOfAnswer();

        var result = new AnswerResponseMapper().apply(answer);

        assertEquals(answer.getId(), result.answerId());
        assertEquals(surveyResponse, result.answer());
    }
}