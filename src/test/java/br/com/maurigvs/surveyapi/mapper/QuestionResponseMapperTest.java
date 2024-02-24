package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.MockData;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionResponseMapperTest {

    @Test
    void should_return_QuestionResponse_given_an_Question() {
        var question = MockData.ofQuestion();
        var choices = MockData.ofSurveyResponse().questions().get(1L).choices();

        var result = new QuestionResponseMapper().apply(question);

        assertEquals(question.getTitle(), result.question());
        assertEquals(choices, result.choices());
    }
}