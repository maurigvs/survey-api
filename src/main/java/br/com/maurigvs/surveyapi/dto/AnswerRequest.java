package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema
public record AnswerRequest(

        @NotNull
        Long surveyId,

        @Valid
        @NotEmpty
        List<AnswerItemRequest> answers
) {
}
