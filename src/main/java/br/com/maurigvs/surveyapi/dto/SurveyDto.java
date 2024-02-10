package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(name = "SurveyRequest")
public record SurveyDto (

        @NotBlank(message = "Survey title can not be blank")
        String survey,

        @Valid @NotEmpty(message = "Survey must have questions")
        List<QuestionDto> questions
) {
}