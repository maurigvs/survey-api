package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.dto.ItemRequest;
import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Item;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

public class EntityBuilder {

    private EntityBuilder(){}

    public static Survey toSurvey(SurveyRequest request){
        Survey survey = new Survey(null, request.survey());

        request.questions().stream()
                .map(question -> toQuestion(question, survey))
                .forEach(survey.getQuestions()::add);
        return survey;
    }

    public static Question toQuestion(QuestionRequest request, Survey survey){
        Question question = new Question(null, request.question(), survey);

        request.choices().stream()
                .map(choice -> toChoice(choice, question))
                .forEach(question.getChoices()::add);
        return question;
    }

    public static Choice toChoice(String request, Question question){
        return new Choice(null, request, question);
    }

    public static Choice toChoice(ChoiceRequest request, Question question){
        return toChoice(request.choice(), question);
    }

    public static Answer toAnswer(AnswerRequest request, Survey survey) {
        Answer answer = new Answer(null, survey);

        request.answers().stream()
                .map(item -> toItem(item, answer))
                .forEach(answer.getItems()::add);
        return answer;
    }

    public static Item toItem(ItemRequest request, Answer answer) {
        Survey survey = answer.getSurvey();
        Question question = findQuestion(request, survey);
        Choice choice = findChoice(request, question);

        return new Item(null, question, choice, answer);
    }

    private static Choice findChoice(ItemRequest request, Question question) {
        return question.getChoices().stream()
                .filter(choice -> choice.getId().equals(request.choiceId()))
                .findFirst().orElseThrow();
    }

    private static Question findQuestion(ItemRequest request, Survey survey) {
        return survey.getQuestions().stream()
                .filter(question -> question.getId().equals(request.questionId()))
                .findFirst().orElseThrow();
    }
}
