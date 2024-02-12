package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mock;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionResponseMapperTest {

    @Test
    void should_return_QuestionResponse_given_an_Question() {
        var question = Mock.ofSurvey().getQuestions().get(0);
        var choices = Mock.ofSurveyResponse().questions().get(1L).choices();

        var result = new QuestionResponseMapper().apply(question);

        assertEquals(question.getTitle(), result.question());
        assertEquals(choices, result.choices());
    }
}