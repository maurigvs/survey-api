package br.com.maurigvs.surveyapi.mocks;

import br.com.maurigvs.surveyapi.model.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.model.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.model.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.model.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.model.entity.Answer;
import br.com.maurigvs.surveyapi.model.entity.AnswerItem;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.model.mapper.DtoMapper;
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

        var q1 = new Question(1L, "Where does Santa Claus live?", survey);
        q1.getChoices().addAll(List.of(
                new Choice(1L, "Hawaii", q1),
                new Choice(2L, "Finland", q1),
                new Choice(3L, "Sweden", q1),
                new Choice(4L, "China", q1)));
        survey.getQuestions().add(q1);

        var q2 = new Question(2L, "Were you satisfied with your Christmas presents?", survey);
        q2.getChoices().addAll(List.of(
                new Choice(5L, "Very satisfied", q2),
                new Choice(6L, "Somewhat satisfied", q2),
                new Choice(7L, "Neither satisfied or dissatisfied", q2),
                new Choice(8L, "Dissatisfied", q2),
                new Choice(9L, "Very dissatisfied", q2)));
        survey.getQuestions().add(q2);
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
                new AnswerRequest.Item(1L,2L),
                new AnswerRequest.Item(2L,7L)
        ));
    }

    public static AnswerRequest.Item ofAnswerRequestItem() {
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

    public static QuestionResponse ofQuestionResponse() {
        return DtoMapper.mapQuestion(ofQuestion());
    }
}
