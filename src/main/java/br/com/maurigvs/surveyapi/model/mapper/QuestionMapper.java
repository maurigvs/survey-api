package br.com.maurigvs.surveyapi.model.mapper;

import br.com.maurigvs.surveyapi.model.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuestionMapper {

    public static Question toEntity(QuestionRequest request, Survey survey) {
        var question = new Question(null, request.question(), survey);
        question.getChoices()
                .addAll(request.choices().stream()
                .map(choice -> ChoiceMapper.toEntity(choice, question))
                .toList());
        return question;
    }

    public static QuestionResponse toResponse(Question question){
        var choiceMap = question.getChoices().stream()
                .collect(Collectors.toMap(Choice::getId, Choice::getTitle));
        return new QuestionResponse(question.getTitle(), choiceMap);
    }
}
