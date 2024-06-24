package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.stream.Collectors;

//TODO Implementar testes!
public final class AnswerMapper {

    public static Answer toEntity(AnswerRequest request, Survey survey){
        var answer = new Answer(null, survey);
        var itemList = request.answers().stream()
                .map(item -> toEntity(item, answer))
                .toList();
        answer.getAnswerItems().addAll(itemList);

        return answer;
    }

    private static AnswerItem toEntity(AnswerRequest.Item item, Answer answer) {

        var question = answer.getSurvey().getQuestions().stream()
                .filter(q -> q.getId().equals(item.questionId()))
                .findFirst()
                .orElseThrow();

        var choice = question.getChoices().stream()
                .filter(c -> c.getId().equals(item.choiceId()))
                .findFirst()
                .orElseThrow();

        return new AnswerItem(null, question, choice, answer);
    }

    public static AnswerResponse toResponse(Answer answer){
        var survey = answer.getSurvey();

        var itemMap = answer.getAnswerItems().stream()
                .collect(Collectors.toMap(AnswerItem::getQuestion, AnswerItem::getChoice));

        survey.getQuestions()
                .removeIf(question -> !itemMap.containsKey(question));

        survey.getQuestions().forEach(question -> question.getChoices()
                .removeIf(choice -> !itemMap.get(question).getId().equals(choice.getId())));

        return new AnswerResponse(answer.getId(), SurveyMapper.toResponse(survey));
    }
}
