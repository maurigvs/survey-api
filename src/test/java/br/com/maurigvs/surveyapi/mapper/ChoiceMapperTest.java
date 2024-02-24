package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceMapperTest {

    @Test
    void should_return_Choice_given_and_ChoiceRequest() {
        var question = MockData.ofQuestion();
        var choiceDto = "China";

        var result = new ChoiceMapper(question).apply(choiceDto);

        assertNull(result.getId());
        assertEquals(choiceDto, result.getTitle());
        assertSame(question, result.getQuestion());
        assertEquals(Collections.emptyList(), result.getAnswerItems());
    }
}