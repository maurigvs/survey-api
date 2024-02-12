package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionResponseMapper implements Function<Question, QuestionResponse> {

    @Override
    public QuestionResponse apply(Question question) {
        var choicesMap = applyChoices(question.getChoices());

        return new QuestionResponse(question.getTitle(), choicesMap);
    }

    private Map<Long, String> applyChoices(List<Choice> choices) {
        return choices.stream().collect(
            Collectors.toMap(Choice::getId, Choice::getTitle)
        );
    }
}
