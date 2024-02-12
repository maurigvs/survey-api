package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerItemRequest;
import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.function.Function;

public class AnswerMapper implements Function<AnswerRequest, Answer> {

    private final Survey survey;

    public AnswerMapper(Survey survey) {
        this.survey = survey;
    }

    @Override
    public Answer apply(AnswerRequest request) {
        var answer = new Answer(null, survey);
        applyItems(answer, request.answers());

        return answer;
    }

    private void applyItems(Answer answer, List<AnswerItemRequest> items) {
        answer.getAnswerItems().addAll(
            items.stream().map(new AnswerItemMapper(answer)).toList()
        );
    }
}
