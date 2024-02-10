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

public class Mocks {

    public static final String TITLE = "Sample Survey";

    public static final String QUESTION_1 = "Where does Santa Claus live?";

    public static final List<String> CHOICES_1 = List.of("Hawaii", "Finland", "Sweden", "China");

    public static final String QUESTION_2 = "Were you satisfied with your Christmas presents?";

    public static final List<String> CHOICES_2 = List.of("Very satisfied", "Somewhat satisfied",
            "Neither satisfied or dissatisfied", "Dissatisfied", "Very dissatisfied");

    public static Survey mockSurvey() {

        var survey = new Survey(1, TITLE);

        var question1 = mockQuestion1(survey);

        var question2 = new Question(2, QUESTION_2, survey);
        question1.getChoices().addAll(List.of(
                new Choice(5, "Very satisfied", question2),
                new Choice(6, "Somewhat satisfied", question2),
                new Choice(7, "Neither satisfied or dissatisfied", question2),
                new Choice(8, "Dissatisfied", question2),
                new Choice(9, "Very dissatisfied", question2)
        ));
        survey.getQuestions().addAll(List.of(question1, question2));

        return survey;
    }

    public static Question mockQuestion1(Survey survey) {
        var question1 = new Question(1, QUESTION_1, survey);
        question1.getChoices().addAll(List.of(
                new Choice(1, "Hawaii", question1),
                new Choice(2, "Finland", question1),
                new Choice(3, "Sweden", question1),
                new Choice(4, "China", question1)
        ));
        return question1;
    }

    public static SurveyDto mockSurveyDto() {
        var questionsDto = List.of(
            new QuestionDto(QUESTION_1, CHOICES_1),
            new QuestionDto(QUESTION_2, CHOICES_2)
        );
        return new SurveyDto(TITLE, questionsDto);
    }

    public static QuestionDto mockQuestionDto() {
        return new QuestionDto(QUESTION_1, CHOICES_1);
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
