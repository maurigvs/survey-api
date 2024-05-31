package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.AnswerItem;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.function.Function;

public class AnswerItemMapper implements Function<AnswerRequest.Item, AnswerItem> {

    private final Answer answer;

    public AnswerItemMapper(Answer answer) {
        this.answer = answer;
    }

    @Override
    public AnswerItem apply(AnswerRequest.Item item) {
        var question = applyQuestion(answer.getSurvey(), item);
        var choice = applyChoice(question, item);

        return new AnswerItem(null, question, choice, answer);
    }

    private Question applyQuestion(Survey survey, AnswerRequest.Item item) {
        return survey.getQuestions().stream()
                .filter(question -> question.getId().equals(item.questionId()))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(item.questionId()));
    }

    private Choice applyChoice(Question question, AnswerRequest.Item request) {
        return question.getChoices().stream()
                .filter(choice -> choice.getId().equals(request.choiceId()))
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException(request.choiceId()));
    }
}
