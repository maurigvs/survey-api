package br.com.maurigvs.surveyapi.model.mapper;

import br.com.maurigvs.surveyapi.model.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.model.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.model.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.entity.Answer;
import br.com.maurigvs.surveyapi.model.entity.AnswerItem;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityMapper {

    public static Survey mapSurvey(SurveyRequest request) {
        var survey = new Survey(null, request.survey());
        survey.getQuestions().addAll(request.questions().stream()
                .map(q -> mapQuestion(q, survey)).toList());
        return survey;
    }

    public static Question mapQuestion(QuestionRequest request, Survey survey) {
        var question = new Question(null, request.question(), survey);
        question.getChoices().addAll(request.choices().stream()
                .map(c -> mapChoice(c, question)).toList());
        return question;
    }

    public static Choice mapChoice(ChoiceRequest request, Question question) {
        return mapChoice(request.choice(), question);
    }

    public static Choice mapChoice(String title, Question question) {
        return new Choice(null, title, question);
    }

    public static Answer mapAnswer(AnswerRequest request, Survey survey){
        var answer = new Answer(null, survey);
        answer.getAnswerItems().addAll(request.answers().stream()
                .map(ai -> mapAnswerItem(ai, answer)).toList());
        return answer;
    }

    private static AnswerItem mapAnswerItem(AnswerRequest.Item item, Answer answer) {
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
}
