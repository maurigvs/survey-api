package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema
public record AnswerItemRequest(

        @NotNull
        Integer questionId,

        @NotNull
        Integer choiceId
) {
}
