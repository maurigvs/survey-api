package br.com.maurigvs.surveyapi.model.mapper;

import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SurveyMapper {

    public static Survey toEntity(SurveyRequest request) {
        var survey = new Survey(null, request.survey());
        survey.getQuestions()
                .addAll(request.questions().stream()
                .map(question -> QuestionMapper.toEntity(question, survey))
                .toList());
        return survey;
    }

    public static SurveyResponse toResponse(Survey survey){
        var questionMap = survey.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, QuestionMapper::toResponse));

        return new SurveyResponse(survey.getId(), survey.getTitle(), questionMap);
    }
}
