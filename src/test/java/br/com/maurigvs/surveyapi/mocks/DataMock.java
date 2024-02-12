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

public class DataMock {

    public static Survey ofSurvey() {
        var survey = new Survey(1L, "Sample Survey");
        survey.getQuestions().addAll(List.of(ofQuestion1(survey), ofQuestion2(survey)));

        return survey;
    }

    public static Question ofQuestion1(Survey survey) {
        var question = new Question(1L, "Where does Santa Claus live?", survey);
        question.getChoices().addAll(
            List.of(
                new Choice(1L, "Hawaii", question),
                new Choice(2L, "Finland", question),
                new Choice(3L, "Sweden", question),
                new Choice(4L, "China", question)));

        return question;
    }

    public static Question ofQuestion2(Survey survey){
        var question = new Question(2L, "Were you satisfied with your Christmas presents?", survey);
        question.getChoices().addAll(
            List.of(
                new Choice(5L, "Very satisfied", question),
                new Choice(6L, "Somewhat satisfied", question),
                new Choice(7L, "Neither satisfied or dissatisfied", question),
                new Choice(8L, "Dissatisfied", question),
                new Choice(9L, "Very dissatisfied", question)
            ));

        return question;
    }

    public static List<Survey> ofSurveyList() {
        return List.of(ofSurvey());
    }

    public static SurveyRequest ofSurveyRequest() {
        return new SurveyRequest("Sample Survey",
                List.of(ofQuestionRequest1(),
                        ofQuestionRequest2()));
    }

    public static QuestionRequest ofQuestionRequest1() {
        return new QuestionRequest("Where does Santa Claus live?",
                List.of("Hawaii",
                        "Finland",
                        "Sweden",
                        "China"));
    }

    private static QuestionRequest ofQuestionRequest2() {
        return new QuestionRequest("Were you satisfied with your Christmas presents?",
                List.of("Very satisfied",
                        "Somewhat satisfied",
                        "Neither satisfied or dissatisfied",
                        "Dissatisfied",
                        "Very dissatisfied"));
    }

    public static SurveyResponse ofSurveyResponse() {
        return new SurveyResponse(1L, "Sample Survey",
                Map.of(1L, ofQuestionResponse1(),
                        2L, ofQuestionResponse2()));
    }

    private static QuestionResponse ofQuestionResponse1() {
        return new QuestionResponse("Where does Santa Claus live?",
                Map.of(1L, "Hawaii",
                        2L, "Finland",
                        3L, "Sweden",
                        4L, "China"));
    }

    private static QuestionResponse ofQuestionResponse2() {
        return new QuestionResponse("Were you satisfied with your Christmas presents?",
                Map.of(5L, "Very satisfied",
                        6L, "Somewhat satisfied",
                        7L, "Neither satisfied or dissatisfied",
                        8L, "Dissatisfied",
                        9L, "Very dissatisfied"));
    }

    public static List<SurveyResponse> ofSurveyResponseList() {
        return List.of(ofSurveyResponse());
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

    public static Answer ofAnswer() {
        var survey = ofSurvey();
        var answer = new Answer(1L, survey);
        answer.getAnswerItems().addAll(List.of(
            new AnswerItem(1L, survey.getQuestions().get(0), survey.getQuestions().get(0).getChoices().get(1), answer),
            new AnswerItem(2L, survey.getQuestions().get(1), survey.getQuestions().get(1).getChoices().get(2), answer)
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

    public static SurveyResponse ofSurveyResponseOfAnswer() {
        return new SurveyResponse(1L, "Sample Survey",
            Map.of(
                1L, new QuestionResponse("Where does Santa Claus live?",
                            Map.of(2L, "Finland")),
                2L, new QuestionResponse("Were you satisfied with your Christmas presents?",
                            Map.of(7L, "Neither satisfied or dissatisfied"))
            ));
    }

    public static AnswerResponse ofAnswerResponse() {
        return new AnswerResponse(1L, ofSurveyResponseOfAnswer());
    }

    public static Question ofQuestion() {
        return ofSurvey().getQuestions().get(0);
    }

    public static Choice ofChoice() {
        return ofQuestion().getChoices().get(0);
    }

    public static ChoiceRequest ofChoiceRequest() {
        return new ChoiceRequest("Argentina");
    }
}
