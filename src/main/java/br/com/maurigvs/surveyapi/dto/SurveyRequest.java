package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema
public record SurveyRequest(

        @NotBlank(message = "Survey title can not be blank")
        String survey,

        @Valid
        @NotEmpty(message = "Survey must have questions")
        List<QuestionRequest> questions
) {
}