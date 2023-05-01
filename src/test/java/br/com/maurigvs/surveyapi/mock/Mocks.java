package br.com.maurigvs.surveyapi.mock;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;

public class Mocks {

    public static SurveyRequest getSurveyRequestValid() {
        SurveyRequest request = new SurveyRequest();
        request.setTitle("Sample Survey");

        QuestionRequest q1 = new QuestionRequest();
        q1.setTitle("Where does Santa Claus live?");
        q1.getChoices().addAll(List.of(
                "Hawaii",
                "Finland",
                "Sweden",
                "China"));
        request.getQuestions().add(q1);

        QuestionRequest q2 = new QuestionRequest();
        q2.setTitle("Where you satisfied with your Christmas presents?");
        q2.getChoices().addAll(List.of(
                "Very satisfied",
                "Somewhat satisfied",
                "Neither satisfied or dissatisfied",
                "Dissatisfied",
                "Very dissatisfied"));
        request.getQuestions().add(q2);
        return request;
    }

    public static Survey getSurveyValid() {
        Survey survey = new Survey("Sample Survey");
        Question q1 = new Question("Where does Santa Claus live?");
        q1.getChoices().addAll(List.of(
                new Choice("Hawaii"),
                new Choice("Finland"),
                new Choice("Sweden"),
                new Choice("China")));
        survey.getQuestions().add(q1);

        Question q2 = new Question("Where you satisfied with your Christmas presents?");
        q2.getChoices().addAll(List.of(
                new Choice("Very satisfied"),
                new Choice("Somewhat satisfied"),
                new Choice("Neither satisfied or dissatisfied"),
                new Choice("Dissatisfied"),
                new Choice("Very dissatisfied")));
        survey.getQuestions().add(q2);
        return survey;
    }

    public static String getSurveyWithoutTitle() {
        return "{'survey':''}";
    }

    public static String parseToJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    public static SurveyRequest getSurveyRequestWithEmptyQuestions() {
        SurveyRequest request = new SurveyRequest();
        request.setTitle("Sample Survey");
        return request;
    }

    public static SurveyRequest getSurveyRequestWithEmptyQuestionTitle() {
        SurveyRequest request = new SurveyRequest();
        request.setTitle("Sample Survey");
        request.getQuestions().add(new QuestionRequest());
        return request;
    }

    public static SurveyRequest getSurveyRequestWithEmptyChoices() {
        SurveyRequest request = new SurveyRequest();
        request.setTitle("Sample Survey");
        QuestionRequest question = new QuestionRequest();
        question.setTitle("Where does Santa Claus live?");
        request.getQuestions().add(question);
        return request;
    }

    public static SurveyRequest getSurveyRequestWithEmptyChoiceTitle() {
        SurveyRequest request = new SurveyRequest();
        request.setTitle("Sample Survey");
        QuestionRequest question = new QuestionRequest();
        question.setTitle("Where does Santa Claus live?");
        question.getChoices().add("  ");
        request.getQuestions().add(question);
        return request;
    }

    public static List<Survey> getSurveyList() {
        Survey survey1 = getSurveyValid();
        Survey survey2 = getSurveyValid();
        return List.of(survey1, survey2);
    }
}