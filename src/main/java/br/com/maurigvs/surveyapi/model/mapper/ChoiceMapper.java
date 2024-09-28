package br.com.maurigvs.surveyapi.model.mapper;

import br.com.maurigvs.surveyapi.model.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ChoiceMapper {

    public static Choice toEntity(ChoiceRequest request, Question question) {
        return toEntity(request.choice(), question);
    }

    public static Choice toEntity(String title, Question question) {
        return new Choice(null, title, question);
    }
}
