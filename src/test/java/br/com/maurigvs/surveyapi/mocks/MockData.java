package br.com.maurigvs.surveyapi.mocks;

import br.com.maurigvs.surveyapi.dto.requests.AnswerItemRequest;
import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
import br.com.maurigvs.surveyapi.dto.requests.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.dto.responses.QuestionResponse;
import br.com.maurigvs.surveyapi.dto.responses.SurveyResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.Map;

public class MockData {

    public static String ofJson(Object object) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Survey ofSurvey() {
        var survey = new Survey(1L, "Sample Survey");

        var question1 = new Question(1L, "Where does Santa Claus live?", survey);
        question1.getChoices().addAll(List.of(
                new Choice(1L, "Hawaii", question1),
                new Choice(2L, "Finland", question1),
                new Choice(3L, "Sweden", question1),
                new Choice(4L, "China", question1)));
        survey.getQuestions().add(question1);

        var question2 = new Question(2L, "Were you satisfied with your Christmas presents?", survey);
        question2.getChoices().addAll(List.of(
                new Choice(5L, "Very satisfied", question2),
                new Choice(6L, "Somewhat satisfied", question2),
                new Choice(7L, "Neither satisfied or dissatisfied", question2),
                new Choice(8L, "Dissatisfied", question2),
                new Choice(9L, "Very dissatisfied", question2)));
        survey.getQuestions().add(question2);

        return survey;
    }

    public static Question ofQuestion() {
        return ofSurvey().getQuestions().get(0);
    }

    public static Choice ofChoice() {
        return ofQuestion().getChoices().get(0);
    }

    public static List<Survey> ofSurveyList() {
        return List.of(ofSurvey());
    }

    public static SurveyRequest ofSurveyRequest() {
        return new SurveyRequest("Sample Survey", List.of(
                new QuestionRequest("Where does Santa Claus live?", List.of(
                        "Hawaii",
                        "Finland",
                        "Sweden",
                        "China")),
                new QuestionRequest("Were you satisfied with your Christmas presents?", List.of(
                        "Very satisfied",
                        "Somewhat satisfied",
                        "Neither satisfied or dissatisfied",
                        "Dissatisfied",
                        "Very dissatisfied"))
        ));
    }

    public static QuestionRequest ofQuestionRequest() {
        return ofSurveyRequest().questions().get(0);
    }

    public static ChoiceRequest ofChoiceRequest() {
        return new ChoiceRequest("Argentina");
    }

    public static SurveyResponse ofSurveyResponse() {
        return new SurveyResponse(1L, "Sample Survey", Map.of(
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
                        9L, "Very dissatisfied"))));
    }

    public static List<SurveyResponse> ofSurveyResponseList() {
        return List.of(ofSurveyResponse());
    }

    public static Answer ofAnswer() {
        var survey = ofSurvey();
        var answer = new Answer(1L, survey);
        answer.getAnswerItems().addAll(List.of(
                new AnswerItem(1L,
                        survey.getQuestions().get(0),
                        survey.getQuestions().get(0).getChoices().get(1),
                        answer
                ),
                new AnswerItem(2L,
                        survey.getQuestions().get(1),
                        survey.getQuestions().get(1).getChoices().get(2),
                        answer
                )
        ));
        return answer;
    }

    public static AnswerRequest ofAnswerRequest() {
        return new AnswerRequest(1L, List.of(
                new AnswerItemRequest(1L,2L),
                new AnswerItemRequest(2L,7L)
        ));
    }

    public static AnswerItemRequest ofAnswerRequestItem() {
        return ofAnswerRequest().answers().get(0);
    }

    public static AnswerResponse ofAnswerResponse() {
        return new AnswerResponse(1L, ofSurveyResponseOfAnswer());
    }

    public static SurveyResponse ofSurveyResponseOfAnswer() {
        return new SurveyResponse(1L, "Sample Survey",
                Map.of(
                        1L, new QuestionResponse("Where does Santa Claus live?",
                                Map.of(2L, "Finland")),
                        2L, new QuestionResponse("Were you satisfied with your Christmas presents?",
                                Map.of(7L, "Neither satisfied or dissatisfied"))
                ));
    }
}
