package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class ChoiceMapper implements Function<String, Choice> {

    private final Question question;

    @Override
    public Choice apply(String choice) {
        return new Choice(null, choice, question);
    }
}
