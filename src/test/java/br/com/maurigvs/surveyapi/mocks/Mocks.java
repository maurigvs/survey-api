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

    private static final String title = "Sample Survey";

    private static final String question1 = "Where does Santa Claus live?";

    private static final List<String> choices1 = List.of("Hawaii", "Finland", "Sweden", "China");

    private static final String question2 = "Were you satisfied with your Christmas presents?";

    private static final List<String> choices2 = List.of("Very satisfied", "Somewhat satisfied",
            "Neither satisfied or dissatisfied", "Dissatisfied", "Very dissatisfied");

    public static Survey mockSurvey() {

        Survey survey = new Survey();
        survey.setTitle(title);

        Question qs1 = new Question();
        qs1.setTitle(question1);
        choices1.forEach(c -> qs1.getChoices().add(new Choice(c, qs1)));
        survey.getQuestions().add(qs1);

        Question qs2 = new Question();
        qs2.setTitle(question2);
        choices2.forEach(c -> qs2.getChoices().add(new Choice(c, qs2)));
        survey.getQuestions().add(qs2);

        return survey;
    }

    public static SurveyDto mockSurveyDto() {

        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setSurvey(title);

        QuestionDto qd1 = new QuestionDto();
        qd1.setQuestion(question1);
        qd1.getChoices().addAll(choices1);
        surveyDto.getQuestions().add(qd1);

        QuestionDto qd2 = new QuestionDto();
        qd2.setQuestion(question2);
        qd2.getChoices().addAll(choices2);
        surveyDto.getQuestions().add(qd2);

        return surveyDto;
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
