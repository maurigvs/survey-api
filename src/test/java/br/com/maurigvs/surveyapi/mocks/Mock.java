package br.com.maurigvs.surveyapi.mocks;

import br.com.maurigvs.surveyapi.dto.AnswerItemRequest;
import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.SurveyResponse;
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

public class Mock {

    public static Survey ofSurvey() {
        var survey = new Survey(1, "Sample Survey");
        survey.getQuestions().addAll(
                List.of(ofQuestion1(survey),
                        ofQuestion2(survey)));

        return survey;
    }

    public static Question ofQuestion1(Survey survey) {
        var question = new Question(1, "Where does Santa Claus live?", survey);
        question.getChoices().addAll(
            List.of(
                new Choice(1, "Hawaii", question),
                new Choice(2, "Finland", question),
                new Choice(3, "Sweden", question),
                new Choice(4, "China", question)
            ));

        return question;
    }

    public static Question ofQuestion2(Survey survey){
        var question = new Question(2, "Were you satisfied with your Christmas presents?", survey);
        question.getChoices().addAll(
            List.of(
                new Choice(5, "Very satisfied", question),
                new Choice(6, "Somewhat satisfied", question),
                new Choice(7, "Neither satisfied or dissatisfied", question),
                new Choice(8, "Dissatisfied", question),
                new Choice(9, "Very dissatisfied", question)
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
        return new SurveyResponse(1, "Sample Survey",
                Map.of(1, ofQuestionResponse1(),
                        2, ofQuestionResponse2()));
    }

    private static QuestionResponse ofQuestionResponse1() {
        return new QuestionResponse("Where does Santa Claus live?",
                Map.of(1, "Hawaii",
                        2, "Finland",
                        3, "Sweden",
                        4, "China"));
    }

    private static QuestionResponse ofQuestionResponse2() {
        return new QuestionResponse("Were you satisfied with your Christmas presents?",
                Map.of(5, "Very satisfied",
                        6, "Somewhat satisfied",
                        7, "Neither satisfied or dissatisfied",
                        8, "Dissatisfied",
                        9, "Very dissatisfied"));
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
        var answer = new Answer(1, survey);
        answer.getAnswerItems().addAll(List.of(
            new AnswerItem(1, survey.getQuestions().get(0), survey.getQuestions().get(0).getChoices().get(1), answer),
            new AnswerItem(2, survey.getQuestions().get(1), survey.getQuestions().get(1).getChoices().get(2), answer)
        ));
        return answer;
    }

    public static AnswerRequest ofAnswerRequest() {
        return new AnswerRequest(1, List.of(
            new AnswerItemRequest(1,2),
            new AnswerItemRequest(2,7)
        ));
    }

    public static AnswerItemRequest ofAnswerRequestItem() {
        return ofAnswerRequest().answers().get(0);
    }

    public static SurveyResponse ofSurveyResponseOfAnswer() {
        return new SurveyResponse(1, "Sample Survey",
            Map.of(
                1, new QuestionResponse("Where does Santa Claus live?",
                            Map.of(2, "Finland")),
                2, new QuestionResponse("Were you satisfied with your Christmas presents?",
                            Map.of(7, "Neither satisfied or dissatisfied"))
            ));
    }

    public static AnswerResponse ofAnswerResponse() {
        return new AnswerResponse(1, ofSurveyResponseOfAnswer());
    }
}
