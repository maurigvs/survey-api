package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyMapperTest {

    @Test
    void should_return_Survey_given_an_SurveyDto() {
        var surveyDto = Mock.ofSurveyRequest();

        var result = new SurveyMapper().apply(surveyDto);

        assertNull(result.getId());
        assertEquals(surveyDto.survey(), result.getTitle());
        assertEquals(surveyDto.questions().size(), result.getQuestions().size());
        assertEquals(Collections.emptyList(), result.getAnswers());
    }
}