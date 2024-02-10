package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyResponseMapperTest {

    @Test
    void should_return_SurveyResponse_given_an_Survey() {
        var survey = Mock.ofSurvey();
        var questions = Mock.ofSurveyResponse().questions();

        var result = new SurveyResponseMapper().apply(survey);

        assertEquals(survey.getId(), result.id());
        assertEquals(survey.getTitle(), result.survey());
        assertEquals(questions, result.questions());
    }

    @Test
    void should_return_QuestionResponse_given_an_Question() {
        var question = Mock.ofSurvey().getQuestions().get(0);
        var choices = Mock.ofSurveyResponse().questions().get(1).choices();

        var result = new SurveyResponseMapper.QuestionResponseMapper().apply(question);

        assertEquals(question.getTitle(), result.question());
        assertEquals(choices, result.choices());
    }
}