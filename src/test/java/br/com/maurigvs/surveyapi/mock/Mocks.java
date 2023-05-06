package br.com.maurigvs.surveyapi.mock;

import br.com.maurigvs.surveyapi.model.*;
import br.com.maurigvs.surveyapi.model.dto.AnswerItemRequest;
import br.com.maurigvs.surveyapi.model.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.model.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Arrays;
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

    public static Survey getSurveySaved(){
        Survey survey = getSurveyValid();
        long id = 1L;
        survey.setId(id);
        for (Question q : survey.getQuestions()) {
            q.setId(id++);
            for (Choice c : q.getChoices()) {
                c.setId(id++);
            }
        }
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

    public static AnswerRequest getAnswerRequestValid() {
        AnswerRequest answer = new AnswerRequest();
        answer.setEmail("maurigvs@icloud.com");
        answer.setSurveyId(1L);

        AnswerItemRequest item1 = new AnswerItemRequest();
        item1.setQuestionId(2L);
        item1.getChoicesIds().addAll(List.of(3L, 4L, 5L));

        AnswerItemRequest item2 = new AnswerItemRequest();
        item2.setQuestionId(6L);
        item2.getChoicesIds().addAll(List.of(7L, 8L));

        answer.getItems().addAll(Arrays.asList(item1, item2));
        return answer;
    }

    public static Answer getAnswerValid() {
        Survey survey = getSurveyValid();
        Answer answer = new Answer();
        answer.setEmail("maurigvs@icloud.com");
        answer.setSurvey(survey);
        for (Question question : survey.getQuestions()) {
            AnswerQuestion aq = new AnswerQuestion();
            aq.setAnswer(answer);
            aq.setQuestion(question);
            for (Choice choice : question.getChoices()) {
                AnswerChoice ac = new AnswerChoice();
                ac.setQuestion(aq);
                ac.setChoice(choice);
            }
        }
        return answer;
    }
}