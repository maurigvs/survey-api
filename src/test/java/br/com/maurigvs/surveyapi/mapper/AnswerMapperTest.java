package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.Answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class AnswerMapperTest {

    @Test
    void should_return_AnswerItem_given_and_AnswerItemRequest() {
        var survey = Mock.ofSurvey();
        var request = Mock.ofAnswerRequest();

        Answer result = new AnswerMapper(survey).apply(request);

        assertNull(result.getId());
        assertSame(survey, result.getSurvey());
        assertEquals(request.answers().size(), result.getAnswerItems().size());
    }
}