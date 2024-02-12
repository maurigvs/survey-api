package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema
public record SurveyResponse(
        Long surveyId,
        String survey,
        Map<Long, QuestionResponse> questions
) {
}