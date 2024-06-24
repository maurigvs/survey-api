package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.responses.QuestionResponse;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.stream.Collectors;

//TODO Implementar testes!
public final class QuestionMapper {

    public static Question toEntity(QuestionRequest request, Survey survey) {
        var question = new Question(null, request.question(), survey);
        var choiceList = request.choices().stream()
                .map(cr -> ChoiceMapper.toEntity(cr, question)).toList();
        question.getChoices().addAll(choiceList);

        return question;
    }

    public static QuestionResponse toResponse(Question question){
        var choiceMap = question.getChoices().stream()
                .collect(Collectors.toMap(Choice::getId, Choice::getTitle));

        return new QuestionResponse(question.getTitle(), choiceMap);
    }
}
