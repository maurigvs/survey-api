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
class QuestionMapperTest {

    @Test
    void should_return_Question_given_an_QuestionRequest() {
        var survey = MockData.ofSurvey();
        var questionRequest = MockData.ofQuestionRequest();

        var result = new QuestionMapper(survey).apply(questionRequest);

        assertNull(result.getId());
        assertEquals(questionRequest.choices().size(), result.getChoices().size());
        assertSame(survey, result.getSurvey());
        assertEquals(Collections.emptyList(), result.getAnswerItems());
    }
}
