package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.SurveyResponse;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.stream.Collectors;

//TODO Implementar testes!
public final class SurveyMapper {

    public static Survey toEntity(SurveyRequest request) {
        var survey = new Survey(null, request.survey());
        var questionList = request.questions().stream()
                .map(qr -> QuestionMapper.toEntity(qr, survey)).toList();
        survey.getQuestions().addAll(questionList);

        return survey;
    }

    public static SurveyResponse toResponse(Survey survey){
        var questionMap = survey.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, QuestionMapper::toResponse));

        return new SurveyResponse(survey.getId(), survey.getTitle(), questionMap);
    }
}
