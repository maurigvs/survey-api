package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SurveyRequest")
public record SurveyDto (

    @NotBlank(message = "Survey title can not be blank")
    String survey,

    @Valid
    @NotEmpty(message = "Survey must have questions")
    List<QuestionDto> questions

) {
}