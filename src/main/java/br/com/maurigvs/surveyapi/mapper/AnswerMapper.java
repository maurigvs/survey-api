package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.AnswerItemRequest;
import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Survey;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class AnswerMapper implements Function<AnswerRequest, Answer> {

    private final Survey survey;

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
