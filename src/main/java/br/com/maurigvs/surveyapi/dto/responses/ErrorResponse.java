package br.com.maurigvs.surveyapi.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema
public record ErrorResponse(
        String error,
        List<String> messages
){

    public ErrorResponse(
            String error,
            String message){

        this(error, List.of(message));
    }
}
