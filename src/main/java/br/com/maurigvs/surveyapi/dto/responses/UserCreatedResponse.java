package br.com.maurigvs.surveyapi.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record UserCreatedResponse(
        String login,
        String createdAt
) {
}
