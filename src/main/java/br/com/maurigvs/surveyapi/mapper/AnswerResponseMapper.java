package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.function.Function;
import java.util.stream.Collectors;

public class AnswerResponseMapper implements Function<Answer, AnswerResponse> {

    @Override
    public AnswerResponse apply(Answer answer) {
        var survey = applySurvey(answer);
        var surveyResponse = new SurveyResponseMapper().apply(survey);

        return new AnswerResponse(answer.getId(), surveyResponse);
    }

    private Survey applySurvey(Answer answer) {
        var survey = answer.getSurvey();
        var itemsMap = answer.getAnswerItems().stream().collect(
            Collectors.toMap(AnswerItem::getQuestion, AnswerItem::getChoice)
        );
        survey.getQuestions().removeIf(
            question -> !itemsMap.containsKey(question)
        );
        survey.getQuestions().forEach(
            question -> question.getChoices().removeIf(
                choice -> !itemsMap.get(question).getId().equals(choice.getId())
            )
        );

        return survey;
    }
}
