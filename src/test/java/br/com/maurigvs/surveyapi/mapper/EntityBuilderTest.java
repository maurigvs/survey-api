package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.dto.ItemRequest;
import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Item;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswer;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswerRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewChoice;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewChoiceRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewQuestion;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewQuestionRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfQuestion;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurvey;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurveyRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EntityBuilderTest {

    @Test
    void should_return_Survey_given_SurveyRequest() {
        SurveyRequest request = mockOfSurveyRequest();
        Survey expected = mockOfSurvey();

        Survey actual = EntityBuilder.toSurvey(request);

        assertNull(actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getQuestions().size(), actual.getQuestions().size());
        assertTrue(actual.getAnswers().isEmpty());
    }

    @Test
    void should_return_Question_given_QuestionRequest_and_Survey() {
        QuestionRequest request = mockOfNewQuestionRequest();
        Survey survey = mockOfSurvey();
        Question expected = mockOfNewQuestion();

        Question actual = EntityBuilder.toQuestion(request, survey);

        assertNull(actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getChoices().size(), actual.getChoices().size());
        assertTrue(actual.getItems().isEmpty());
    }

    @Test
    void should_return_Choice_given_ChoiceRequest() {
        ChoiceRequest request = mockOfNewChoiceRequest();
        Question question = mockOfQuestion();
        Choice expected = mockOfNewChoice(question);

        Choice actual = EntityBuilder.toChoice(request, question);

        assertNull(actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertSame(expected.getQuestion(), actual.getQuestion());
        assertEquals(expected.getItems().size(), actual.getItems().size());
    }

    @Test
    void should_return_Choice_given_a_String() {
        String request = "New Choice";
        Question question = mockOfQuestion();
        Choice expected = new Choice(null, request, question);

        Choice actual = EntityBuilder.toChoice(request, question);

        assertNull(actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertSame(expected.getQuestion(), actual.getQuestion());
        assertEquals(expected.getItems().size(), actual.getItems().size());
    }

    @Test
    void should_return_Answer_given_AnswerRequest_and_Survey() {
        AnswerRequest request = mockOfAnswerRequest();
        Survey survey = mockOfSurvey();
        Answer expected = mockOfAnswer();

        Answer actual = EntityBuilder.toAnswer(request, survey);

        assertNull(actual.getId());
        assertSame(survey, actual.getSurvey());
        assertEquals(expected.getItems().size(), actual.getItems().size());
    }

    @Test
    void should_return_Item_given_ItemRequest_and_Answer() {
        ItemRequest itemRequest = mockOfAnswerRequest().answers().get(0);
        Answer answer = mockOfAnswer();
        Item expected = answer.getItems().get(0);

        Item actual = EntityBuilder.toItem(itemRequest, answer);

        assertNull(actual.getId());
        assertSame(expected.getQuestion(), actual.getQuestion());
        assertSame(expected.getChoice(), actual.getChoice());
        assertSame(answer, actual.getAnswer());
    }
}