package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionMapperTest {

    @Test
    void should_return_Question_given_and_QuestionDto() {
        var survey = Mock.ofSurvey();
        var questionDto = Mock.ofQuestionRequest1();

        var result = new QuestionMapper(survey).apply(questionDto);

        assertNull(result.getId());
        assertEquals(questionDto.choices().size(), result.getChoices().size());
        assertSame(survey, result.getSurvey());
        assertEquals(Collections.emptyList(), result.getAnswerItems());
    }
}
