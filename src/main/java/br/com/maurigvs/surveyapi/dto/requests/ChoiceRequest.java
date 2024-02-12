package br.com.maurigvs.surveyapi.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record ChoiceRequest(
        String choice
) {
}
