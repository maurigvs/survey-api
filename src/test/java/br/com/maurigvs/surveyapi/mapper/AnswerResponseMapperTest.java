package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerResponseMapperTest {

    @Test
    void should_return_SurveyResponse_given_an_Answer() {
        var survey = Mock.ofSurvey();
        var answer = Mock.ofAnswer();
        var detail = Mock.ofSurveyResponseOfAnswer();

        var result = new AnswerResponseMapper(survey).apply(answer);

        assertEquals(answer.getId(), result.id());
        assertEquals(detail, result.survey());
    }
}