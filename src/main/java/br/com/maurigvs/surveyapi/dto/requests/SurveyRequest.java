package br.com.maurigvs.surveyapi.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema
public record SurveyRequest(

        @NotBlank
        String survey,

        @Valid
        @NotEmpty
        List<QuestionRequest> questions
) {
}