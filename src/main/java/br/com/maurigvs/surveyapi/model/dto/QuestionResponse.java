package br.com.maurigvs.surveyapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema
public record QuestionResponse(
        String question,
        Map<Long, String> choices
){
}
