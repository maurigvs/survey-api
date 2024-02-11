package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerItemRequest;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;

import java.util.function.Function;

public class AnswerItemMapper implements Function<AnswerItemRequest, AnswerItem> {

    private final Answer answer;

    public AnswerItemMapper(Answer answer) {
        this.answer = answer;
    }

    @Override
    public AnswerItem apply(AnswerItemRequest request) {
        return new AnswerItem(null, request.questionId(), request.choiceId(), answer);
    }
}
