package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.function.Function;

public class SurveyMapper implements Function<SurveyRequest, Survey> {

    @Override
    public Survey apply(SurveyRequest request) {
        var survey = new Survey(null, request.survey());
        applyQuestions(survey, request.questions());

        return survey;
    }

    private void applyQuestions(Survey survey, List<QuestionRequest> questions) {
        survey.getQuestions().addAll(
            questions.stream().map(new QuestionMapper(survey)).toList()
        );
    }
}
