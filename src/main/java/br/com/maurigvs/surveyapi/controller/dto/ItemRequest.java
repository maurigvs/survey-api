package br.com.maurigvs.surveyapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema
public record ItemRequest(

        @NotNull
        Long questionId,

        @NotNull
        Long choiceId
) {
}
