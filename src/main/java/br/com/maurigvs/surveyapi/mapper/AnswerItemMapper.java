package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerItemRequest;
import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.function.Function;

public class AnswerItemMapper implements Function<AnswerItemRequest, AnswerItem> {

    private final Answer answer;

    public AnswerItemMapper(Answer answer) {
        this.answer = answer;
    }

    @Override
    public AnswerItem apply(AnswerItemRequest request) {
        var question = applyQuestion(answer.getSurvey(), request);
        var choice = applyChoice(question, request);

        return new AnswerItem(null, question, choice, answer);
    }

    private Question applyQuestion(Survey survey, AnswerItemRequest request) {
        return survey.getQuestions().stream()
                .filter(question -> question.getId().equals(request.questionId()))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(request.questionId()));
    }

    private Choice applyChoice(Question question, AnswerItemRequest request) {
        return question.getChoices().stream()
                .filter(choice -> choice.getId().equals(request.choiceId()))
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException(request.choiceId()));
    }
}
