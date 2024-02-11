package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerItemMapperTest {

    @Test
    void should_return_AnswerItem_given_and_AnswerItemRequest() {
        var answer = Mock.ofAnswer();
        var request = Mock.ofAnswerRequestItem();

        AnswerItem result = new AnswerItemMapper(answer).apply(request);

        assertNull(result.getId());
        assertEquals(request.questionId(), result.getQuestionId());
        assertEquals(request.choiceId(), result.getChoiceId());
        assertSame(answer, result.getAnswer());
    }
}