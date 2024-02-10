package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyMapperTest {

    @Test
    void should_return_Survey_given_an_SurveyDto() {
        var surveyDto = Mock.ofSurveyRequest();

        var result = new SurveyMapper().apply(surveyDto);

        assertNull(result.getId());
        assertEquals(surveyDto.survey(), result.getTitle());
        assertEquals(surveyDto.questions().size(), result.getQuestions().size());
    }

    @Test
    void should_return_Question_given_and_QuestionDto() {
        var survey = Mock.ofSurvey();
        var questionDto = Mock.ofQuestionRequest1();

        var result = new SurveyMapper.QuestionMapper(survey).apply(questionDto);

        assertNull(result.getId());
        assertEquals(questionDto.choices().size(), result.getChoices().size());
        assertSame(survey, result.getSurvey());
    }

    @Test
    void should_return_Choice_given_and_ChoiceDto() {
        var question = Mock.ofSurvey().getQuestions().get(0);
        var choiceDto = "China";

        var result = new SurveyMapper.ChoiceMapper(question).apply(choiceDto);

        assertNull(result.getId());
        assertEquals(choiceDto, result.getTitle());
        assertSame(question, result.getQuestion());
    }
}