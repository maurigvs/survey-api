package br.com.maurigvs.surveyapi.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema
public record AnswerItemRequest(

        @NotNull
        Long questionId,

        @NotNull
        Long choiceId
) {
}
