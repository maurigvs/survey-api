package br.com.maurigvs.surveyapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema
public record ChoiceRequest(

        @NotBlank
        String choice
) {
}
