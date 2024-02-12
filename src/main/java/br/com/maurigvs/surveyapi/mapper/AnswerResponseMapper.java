package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.function.Function;
import java.util.stream.Collectors;

public class AnswerResponseMapper implements Function<Answer, AnswerResponse> {

    @Override
    public AnswerResponse apply(Answer answer) {
        var survey = filterSurveyChoices(answer);
        var surveyResponse = new SurveyResponseMapper().apply(survey);

        return new AnswerResponse(answer.getId(), surveyResponse);
    }

    private Survey filterSurveyChoices(Answer answer) {
        var survey = answer.getSurvey();
        var idsMap = answer.getAnswerItems().stream().collect(
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
        return survey;
    }
}
