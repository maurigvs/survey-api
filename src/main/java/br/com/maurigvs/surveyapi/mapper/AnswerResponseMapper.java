package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.function.Function;
import java.util.stream.Collectors;

public class AnswerResponseMapper implements Function<Answer, AnswerResponse> {

    private final Survey survey;

    public AnswerResponseMapper(Survey survey) {
        this.survey = survey;
    }

    @Override
    public AnswerResponse apply(Answer answer) {
        filterSurveyChoices(survey, answer);
        return new AnswerResponse(
                answer.getId(),
                new SurveyResponseMapper().apply(survey));
    }

    private void filterSurveyChoices(Survey survey, Answer answer) {
        var idsMap = answer.getItems().stream().collect(
            Collectors.toMap(AnswerItem::getQuestionId, AnswerItem::getChoiceId)
        );
        survey.getQuestions().removeIf(
            question -> !idsMap.containsKey(question.getId())
        );
        survey.getQuestions().forEach(
            question -> question.getChoices().removeIf(
                choice -> !idsMap.get(question.getId()).equals(choice.getId())
            )
        );
    }
}
