package br.com.maurigvs.surveyapi.mocks;

import br.com.maurigvs.surveyapi.controller.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.controller.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.controller.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.controller.dto.ItemRequest;
import br.com.maurigvs.surveyapi.controller.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.controller.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.controller.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.controller.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Item;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.Map;

public class MockData {

    public static Survey mockOfSurvey() {
        Survey survey = new Survey(1L, "Christmas Survey");
        Question question1 = new Question(1L, "Where does Santa Claus live?", survey);
        List<Choice> choices1 = List.of(
                new Choice(1L, "Hawaii", question1),
                new Choice(2L, "Finland", question1),
                new Choice(3L, "Sweden", question1),
                new Choice(4L, "China", question1));
        Question question2 = new Question(2L, "Were you satisfied with your Christmas presents?", survey);
        List<Choice> choices2 = List.of(
                new Choice(5L, "Very satisfied", question2),
                new Choice(6L, "Somewhat satisfied", question2),
                new Choice(7L, "Neither satisfied or dissatisfied", question2),
                new Choice(8L, "Dissatisfied", question2),
                new Choice(9L, "Very dissatisfied", question2));

        survey.getQuestions().addAll(List.of(question1, question2));
        question1.getChoices().addAll(choices1);
        question2.getChoices().addAll(choices2);
        return survey;
    }

    public static SurveyRequest mockOfSurveyRequest() {
        return new SurveyRequest("Christmas Survey", List.of(
                new QuestionRequest("Where does Santa Claus live?", List.of(
                        "Hawaii",
                        "Finland",
                        "Sweden",
                        "China")),
                new QuestionRequest("Were you satisfied with your Christmas presents?", List.of(
                        "Very satisfied",
                        "Somewhat satisfied",
                        "Neither satisfied or dissatisfied",
                        "Dissatisfied", "Very dissatisfied"))
        ));
    }

    public static SurveyResponse mockOfSurveyResponse() {
        return new SurveyResponse(1L, "Christmas Survey", Map.of(
                1L, new QuestionResponse("Where does Santa Claus live?", Map.of(
                        1L, "Hawaii",
                        2L, "Finland",
                        3L, "Sweden",
                        4L, "China")),
                2L, new QuestionResponse("Were you satisfied with your Christmas presents?", Map.of(
                        5L, "Very satisfied",
                        6L, "Somewhat satisfied",
                        7L, "Neither satisfied or dissatisfied",
                        8L, "Dissatisfied",
                        9L, "Very dissatisfied"))
        ));
    }

    public static Question mockOfNewQuestion() {
        Survey survey = new Survey(1L, "Christmas Survey");
        Question question = new Question(3L, "What is your favorite Christmas song?", survey);
        List<Choice> choices = List.of(
                new Choice(10L, "Jingle Bells", question),
                new Choice(11L, "Silent Night", question),
                new Choice(12L, "White Christmas", question),
                new Choice(13L, "All I Want for Christmas Is You", question),
                new Choice(14L, "Last Christmas", question));

        survey.getQuestions().add(question);
        question.getChoices().addAll(choices);
        return question;
    }

    public static QuestionRequest mockOfNewQuestionRequest() {
        return new QuestionRequest("What is your favorite Christmas song?", List.of(
                "Jingle Bells",
                "Silent Night",
                "White Christmas",
                "All I Want for Christmas Is You",
                "Last Christmas"));
    }

    public static Choice mockOfNewChoice(Question question) {
        return new Choice(15L, "Alaska", question);
    }

    public static ChoiceRequest mockOfNewChoiceRequest() {
        return new ChoiceRequest("Alaska");
    }

    public static Answer mockOfAnswer() {
        Survey survey = new Survey(1L, "Christmas Survey");
        Answer answer = new Answer(1L, survey);
        Question question1 = new Question(1L, "Where does Santa Claus live?", survey);
        Choice choice1Selected = new Choice(2L, "Finland", question1);
        List<Choice> choices1 = List.of(
                new Choice(1L, "Hawaii", question1),
                choice1Selected,
                new Choice(3L, "Sweden", question1),
                new Choice(4L, "China", question1));
        Question question2 = new Question(2L, "Were you satisfied with your Christmas presents?", survey);
        Choice choice2selected = new Choice(7L, "Neither satisfied or dissatisfied", question2);
        List<Choice> choices2 = List.of(
                new Choice(5L, "Very satisfied", question2),
                new Choice(6L, "Somewhat satisfied", question2),
                choice2selected,
                new Choice(8L, "Dissatisfied", question2),
                new Choice(9L, "Very dissatisfied", question2));
        Item item1 = new Item(1L, question1, choice1Selected, answer);
        Item item2 = new Item(2L, question2, choice2selected, answer);

        survey.getQuestions().addAll(List.of(question1, question2));
        question1.getChoices().addAll(choices1);
        question2.getChoices().addAll(choices2);
        answer.getItems().addAll(List.of(item1, item2));
        return answer;
    }

    public static AnswerRequest mockOfAnswerRequest() {
        return new AnswerRequest(1L, List.of(
                new ItemRequest(1L, 2L),
                new ItemRequest(2L, 7L)));
    }

    public static AnswerResponse mockOfAnswerResponse() {
        return new AnswerResponse(1L,
                new SurveyResponse(1L, "Christmas Survey", Map.of(
                        1L, new QuestionResponse("Where does Santa Claus live?", Map.of(
                                2L, "Finland")),
                        2L, new QuestionResponse("Were you satisfied with your Christmas presents?", Map.of(
                                7L, "Neither satisfied or dissatisfied"))
                )));
    }

    public static String ofJson(Object object) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Question mockOfQuestion() {
        return mockOfSurvey().getQuestions().get(0);
    }

    public static Choice mockOfChoice() {
        return mockOfQuestion().getChoices().get(0);
    }
}
