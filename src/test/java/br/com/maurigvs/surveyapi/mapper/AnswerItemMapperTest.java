package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.AnswerItemRequest;
import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerItemMapperTest {

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = MockData.ofAnswer();
    }

    @Test
    void should_return_AnswerItem_given_and_AnswerItemRequest() {
        var request = MockData.ofAnswerRequestItem();

        AnswerItem result = new AnswerItemMapper(answer).apply(request);

        assertNull(result.getId());
        assertEquals(request.questionId(), result.getQuestion().getId());
        assertEquals(request.choiceId(), result.getChoice().getId());
        assertSame(answer, result.getAnswer());
    }

    @Test
    void should_throw_exception_when_question_not_found_in_a_given_survey() {
        var request = new AnswerItemRequest(10L,15L);

        var exception = assertThrows(QuestionNotFoundException.class,
                () -> new AnswerItemMapper(answer).apply(request));

        assertEquals("Question not found by Id 10", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_choice_not_found_in_a_given_survey() {
        var request = new AnswerItemRequest(2L,20L);

        var exception = assertThrows(ChoiceNotFoundException.class,
                () -> new AnswerItemMapper(answer).apply(request));

        assertEquals("Choice not found by Id 20", exception.getMessage());
    }
}