package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.Choice;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyDtoMapperTest {

    @Test
    void should_return_SurveyDto_given_an_Survey() {
        var survey = Mock.ofSurvey();

        var result = new SurveyDtoMapper().apply(survey);

        assertEquals(survey.getTitle(), result.survey());
        assertEquals(survey.getQuestions().size(), result.questions().size());
    }

    @Test
    void should_return_QuestionDto_given_and_Question() {
        var question = Mock.ofSurvey().getQuestions().get(0);
        var choices = question.getChoices().stream().map(Choice::getTitle).toList();

        var result = new SurveyDtoMapper.QuestionDtoMapper().apply(question);

        assertEquals(question.getTitle(), result.question());
        assertLinesMatch(choices, result.choices());
    }
}