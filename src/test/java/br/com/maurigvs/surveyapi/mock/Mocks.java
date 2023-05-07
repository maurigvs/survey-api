package br.com.maurigvs.surveyapi.mock;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.maurigvs.surveyapi.entity.dto.AnswerItemRequest;
import br.com.maurigvs.surveyapi.entity.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.entity.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.entity.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.entity.model.Answer;
import br.com.maurigvs.surveyapi.entity.model.AnswerChoice;
import br.com.maurigvs.surveyapi.entity.model.AnswerQuestion;
import br.com.maurigvs.surveyapi.entity.model.Survey;
import br.com.maurigvs.surveyapi.entity.model.SurveyChoice;
import br.com.maurigvs.surveyapi.entity.model.SurveyQuestion;

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
        SurveyQuestion q1 = new SurveyQuestion("Where does Santa Claus live?");
        q1.getChoices().addAll(List.of(
                new SurveyChoice("Hawaii"),
                new SurveyChoice("Finland"),
                new SurveyChoice("Sweden"),
                new SurveyChoice("China")));
        survey.getQuestions().add(q1);

        SurveyQuestion q2 = new SurveyQuestion("Where you satisfied with your Christmas presents?");
        q2.getChoices().addAll(List.of(
                new SurveyChoice("Very satisfied"),
                new SurveyChoice("Somewhat satisfied"),
                new SurveyChoice("Neither satisfied or dissatisfied"),
                new SurveyChoice("Dissatisfied"),
                new SurveyChoice("Very dissatisfied")));
        survey.getQuestions().add(q2);
        return survey;
    }

    public static Survey getSurveySaved(){
        Survey survey = getSurveyValid();
        long id = 1L;
        survey.setId(id);
        for (SurveyQuestion q : survey.getQuestions()) {
            q.setId(id++);
            for (SurveyChoice c : q.getChoices()) {
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
        for (SurveyQuestion question : survey.getQuestions()) {
            AnswerQuestion aq = new AnswerQuestion();
            aq.setAnswer(answer);
            aq.setQuestion(question);
            for (SurveyChoice choice : question.getChoices()) {
                AnswerChoice ac = new AnswerChoice();
                ac.setQuestion(aq);
                ac.setChoice(choice);
            }
        }
        return answer;
    }
}