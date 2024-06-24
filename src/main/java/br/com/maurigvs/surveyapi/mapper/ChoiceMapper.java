package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;

//TODO Implementar testes!
public final class ChoiceMapper {

    public static Choice toEntity(ChoiceRequest request, Question question) {
        return toEntity(request.choice(), question);
    }

    public static Choice toEntity(String title, Question question) {
        return new Choice(null, title, question);
    }
}
