package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class SurveyDto {

    @NotBlank(message = "Survey title can not be blank")
    private String survey;

    private final List<QuestionDto> questions = new ArrayList<>();

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }
}
