package br.com.maurigvs.surveyapi.controller.dto;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Item;
import br.com.maurigvs.surveyapi.model.Question;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.stream.Collectors;

@Schema
public record QuestionResponse(
        String question,
        Map<Long, String> choices
) {

    public QuestionResponse(Question question) {
        this(question.getTitle(),
                question.getChoices().stream().collect(Collectors
                        .toMap(Choice::getId, Choice::getTitle)));
    }

    public QuestionResponse(Item item){
        this(item.getQuestion().getTitle(),
                Map.of(item.getChoice().getId(), item.getChoice().getTitle()));
    }
}
