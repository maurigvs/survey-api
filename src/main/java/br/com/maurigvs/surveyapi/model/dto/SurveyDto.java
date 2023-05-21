package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SurveyRequest")
public class SurveyDto {

    @NotBlank(message = "Survey title can not be blank")
    private String survey;

    @Valid
    @NotEmpty(message = "Survey must have questions")
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
