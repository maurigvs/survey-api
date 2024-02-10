package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema
public record SurveyResponse(
        Integer id,
        String survey,
        Map<Integer, QuestionResponse> questions
) {
}