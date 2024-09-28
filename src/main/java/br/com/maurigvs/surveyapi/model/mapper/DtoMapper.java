package br.com.maurigvs.surveyapi.model.mapper;

import br.com.maurigvs.surveyapi.model.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.model.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.model.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.model.entity.Answer;
import br.com.maurigvs.surveyapi.model.entity.AnswerItem;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoMapper {

    public static SurveyResponse mapSurvey(Survey survey){
        return new SurveyResponse(
                survey.getId(),
                survey.getTitle(),
                survey.getQuestions().stream().collect(Collectors
                        .toMap(Question::getId, DtoMapper::mapQuestion)));
    }

    private static SurveyResponse mapSurvey(Answer answer){
        return new SurveyResponse(
                answer.getSurvey().getId(),
                answer.getSurvey().getTitle(),
                answer.getAnswerItems().stream().collect(Collectors
                        .toMap(item -> item.getQuestion().getId(), DtoMapper::mapQuestion)));
    }

    public static QuestionResponse mapQuestion(Question question){
        return new QuestionResponse(
                question.getTitle(),
                question.getChoices().stream().collect(Collectors
                        .toMap(Choice::getId, Choice::getTitle)));
    }

    private static QuestionResponse mapQuestion(AnswerItem answerItem){
        return new QuestionResponse(
                answerItem.getQuestion().getTitle(),
                Map.of(answerItem.getChoice().getId(), answerItem.getChoice().getTitle()));
    }

    public static AnswerResponse mapAnswer(Answer answer){
        return new AnswerResponse(
                answer.getId(),
                mapSurvey(answer));
    }
}
