package br.com.maurigvs.surveyapi.exception;

public class QuestionNotFoundException extends BadRequestException {

    public QuestionNotFoundException(Integer questionId) {
        super("Question not found by Id " + questionId);
    }
}
