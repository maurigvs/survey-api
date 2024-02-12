package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;

import java.util.function.Function;

public class ChoiceMapper implements Function<String, Choice> {

    private final Question question;

    public ChoiceMapper(Question question) {
        this.question = question;
    }

    @Override
    public Choice apply(String choice) {
        return new Choice(null, choice, question);
    }
}
