package br.com.maurigvs.surveyapi.mocks;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.maurigvs.surveyapi.model.dto.QuestionDto;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;

public class Mocks {

    public static final String TITLE = "Sample Survey";

    public static final String QUESTION_1 = "Where does Santa Claus live?";

    public static final List<String> CHOICES_1 = List.of("Hawaii", "Finland", "Sweden", "China");

    public static final String QUESTION_2 = "Were you satisfied with your Christmas presents?";

    public static final List<String> CHOICES_2 = List.of("Very satisfied", "Somewhat satisfied",
            "Neither satisfied or dissatisfied", "Dissatisfied", "Very dissatisfied");

    public static Survey mockSurvey() {

        Survey survey = new Survey();
        survey.setTitle(TITLE);

        Question qs1 = new Question();
        qs1.setTitle(QUESTION_1);
        CHOICES_1.forEach(c -> qs1.getChoices().add(new Choice(c, qs1)));
        survey.getQuestions().add(qs1);

        Question qs2 = new Question();
        qs2.setTitle(QUESTION_2);
        CHOICES_2.forEach(c -> qs2.getChoices().add(new Choice(c, qs2)));
        survey.getQuestions().add(qs2);

        return survey;
    }

    public static SurveyDto mockSurveyDto() {
        var questionsDto = List.of(
            new QuestionDto(QUESTION_1, CHOICES_1),
            new QuestionDto(QUESTION_2, CHOICES_2)
        );
        return new SurveyDto(TITLE, questionsDto);
    }

    public static Survey mockSurveyWithIds(){
        int bound = 10000;
        Random random = new Random();
        Survey survey = mockSurvey();
        survey.setId(random.nextInt(bound));
        survey.getQuestions().forEach(q -> q.setId(random.nextInt(bound)));
        survey.getQuestions().forEach(q -> q.getChoices().forEach(c -> c.setId(random.nextInt(bound))));
        return survey;
    }

    public static String parseToJson(Object object) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            return om.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
