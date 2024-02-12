package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.DataMock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyResponseMapperTest {

    @Test
    void should_return_SurveyResponse_given_an_Survey() {
        var survey = DataMock.ofSurvey();
        var questions = DataMock.ofSurveyResponse().questions();

        var result = new SurveyResponseMapper().apply(survey);

        assertEquals(survey.getId(), result.surveyId());
        assertEquals(survey.getTitle(), result.survey());
        assertEquals(questions, result.questions());
    }
}
