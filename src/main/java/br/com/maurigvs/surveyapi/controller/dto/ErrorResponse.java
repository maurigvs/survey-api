package br.com.maurigvs.surveyapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.util.List;

@Schema
public record ErrorResponse(
        String error,
        List<String> messages
) {

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this(httpStatus.getReasonPhrase(), List.of(message));
    }
}
