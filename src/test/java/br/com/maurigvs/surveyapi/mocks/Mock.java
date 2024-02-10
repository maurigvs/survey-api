package br.com.maurigvs.surveyapi.mocks;

import br.com.maurigvs.surveyapi.model.dto.QuestionDto;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class Mock {

    public static Survey ofSurvey() {
        var survey = new Survey(1, "Sample Survey");
        survey.getQuestions().addAll(
            List.of(
                ofQuestion1(survey),
                ofQuestion2(survey)
            )
        );

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
            )
        );

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
            )
        );

        return question;
    }

    public static List<Survey> ofSurveyList() {
        return List.of(ofSurvey());
    }

    public static SurveyDto ofSurveyDto() {
        return new SurveyDto("Sample Survey", List.of(
            ofQuestionDto1(),
            ofQuestionDto2())
        );
    }

    public static QuestionDto ofQuestionDto1() {
        return new QuestionDto("Where does Santa Claus live?",
                List.of("Hawaii", "Finland", "Sweden", "China"));
    }

    public static QuestionDto ofQuestionDto2() {
        return new QuestionDto("Were you satisfied with your Christmas presents?",
                List.of("Very satisfied", "Somewhat satisfied",
                        "Neither satisfied or dissatisfied", "Dissatisfied",
                        "Very dissatisfied"));
    }

    public static String toJson(Object object) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
