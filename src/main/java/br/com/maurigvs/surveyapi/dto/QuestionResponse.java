package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(name = "QuestionResponse")
public record QuestionResponse(
        String question,
        Map<Integer, String> choices
){
}