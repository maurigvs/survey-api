package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceMapperTest {

    @Test
    void should_return_Choice_given_and_ChoiceDto() {
        var question = Mock.ofSurvey().getQuestions().get(0);
        var choiceDto = "China";

        var result = new ChoiceMapper(question).apply(choiceDto);

        assertNull(result.getId());
        assertEquals(choiceDto, result.getTitle());
        assertSame(question, result.getQuestion());
    }
}