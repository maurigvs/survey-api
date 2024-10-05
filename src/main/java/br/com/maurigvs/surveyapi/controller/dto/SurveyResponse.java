package br.com.maurigvs.surveyapi.controller.dto;

import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.stream.Collectors;

@Schema
public record SurveyResponse(
        Long surveyId,
        String survey,
        Map<Long, QuestionResponse> questions
) {

    public SurveyResponse(Survey survey) {
        this(survey.getId(),
                survey.getTitle(),
                survey.getQuestions().stream().collect(Collectors
                        .toMap(Question::getId, QuestionResponse::new)));
    }

    public SurveyResponse(Answer answer){
        this(answer.getSurvey().getId(),
                answer.getSurvey().getTitle(),
                answer.getItems().stream().collect(Collectors
                        .toMap(item -> item.getQuestion().getId(), QuestionResponse::new)));
    }
}
