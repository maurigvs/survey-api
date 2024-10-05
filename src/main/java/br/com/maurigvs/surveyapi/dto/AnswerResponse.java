package br.com.maurigvs.surveyapi.dto;

import br.com.maurigvs.surveyapi.model.Answer;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record AnswerResponse(
        Long answerId,
        SurveyResponse answer
) {

    public AnswerResponse(Answer answer) {
        this(answer.getId(), new SurveyResponse(answer));
    }
}
