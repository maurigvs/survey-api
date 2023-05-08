package br.com.maurigvs.surveyapi;

import java.util.Arrays;
import java.util.List;

import br.com.maurigvs.surveyapi.entity.dto.SurveyDto;
import br.com.maurigvs.surveyapi.entity.dto.SurveyItemDto;
import br.com.maurigvs.surveyapi.entity.model.Survey;
import br.com.maurigvs.surveyapi.entity.model.SurveyChoice;
import br.com.maurigvs.surveyapi.entity.model.SurveyQuestion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Mocks {

    public static String parseToJson(Object object) throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    public static SurveyDto surveyDto(){
        SurveyDto dto = new SurveyDto();
        dto.setTitle("Sample Survey");

        SurveyItemDto item1 = new SurveyItemDto();
        item1.setTitle("Where does Santa Claus live?");
        item1.getChoices().addAll(List.of("Hawaii", "Finland", "Sweden", "China"));

        SurveyItemDto item2 = new SurveyItemDto();
        item2.setTitle("Were you satisfied with your Christmas presents?");
        item2.getChoices().addAll(List.of("Very satisfied", "Somewhat satisfied",
                "Neither satisfied or dissatisfied", "Dissatisfied", "Very dissatisfied"));
        dto.getItems().addAll(Arrays.asList(item1, item2));
        return dto;
    }

    public static Survey survey(){
        Survey survey = new Survey("Sample Survey");
        SurveyQuestion question1 = new SurveyQuestion("Where does Santa Claus live?");
        question1.getChoices().addAll(Arrays.asList(
                new SurveyChoice("Hawaii"),
                new SurveyChoice("Finland"),
                new SurveyChoice("Sweden"),
                new SurveyChoice("China")));
        survey.getQuestions().add(question1);

        SurveyQuestion question2 = new SurveyQuestion("Were you satisfied with your Christmas presents?");
        question2.getChoices().addAll(Arrays.asList(
                new SurveyChoice("Very satisfied"),
                new SurveyChoice("Somewhat satisfied"),
                new SurveyChoice("Neither satisfied or dissatisfied"),
                new SurveyChoice("Dissatisfied"),
                new SurveyChoice("Very dissatisfied")));
        survey.getQuestions().add(question2);
        return survey;
    }

    public static List<Survey> listOfSurvey() {
        return List.of(survey());
    }
}
