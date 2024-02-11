package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.Answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnswerMapperTest {

    @Test
    void should_return_AnswerItem_given_and_AnswerItemRequest() {
        var request = Mock.ofAnswerRequest();

        Answer result = new AnswerMapper().apply(request);

        assertNull(result.getId());
        assertEquals(request.surveyId(), result.getSurveyId());
        assertEquals(request.answers().size(), result.getItems().size());
    }
}