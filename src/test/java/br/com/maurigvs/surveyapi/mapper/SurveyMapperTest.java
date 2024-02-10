package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.mocks.Mocks;
import br.com.maurigvs.surveyapi.model.dto.QuestionDto;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
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
        // given
        SurveyDto surveyDto = Mocks.mockSurveyDto();

        // when
        Survey result = new SurveyMapper().apply(surveyDto);

        // then
        assertNull(result.getId());
        assertEquals(surveyDto.survey(), result.getTitle());
        assertEquals(surveyDto.questions().size(), result.getQuestions().size());
    }

    @Test
    void should_return_Question_given_and_QuestionDto() {
        // given
        Survey survey = Mocks.mockSurvey();
        QuestionDto questionDto = Mocks.mockQuestionDto();

        // when
        Question result = new SurveyMapper.QuestionMapper(survey).apply(questionDto);

        // then
        assertNull(result.getId());
        assertEquals(questionDto.choices().size(), result.getChoices().size());
        assertSame(survey, result.getSurvey());
    }

    @Test
    void should_return_Choice_given_and_ChoiceDto() {
        // given
        Question question = Mocks.mockQuestion1(Mocks.mockSurvey());
        String choiceDto = "China";

        // when
        Choice result = new SurveyMapper.ChoiceMapper(question).apply(choiceDto);

        // then
        assertNull(result.getId());
        assertEquals(choiceDto, result.getTitle());
        assertSame(question, result.getQuestion());
    }
}