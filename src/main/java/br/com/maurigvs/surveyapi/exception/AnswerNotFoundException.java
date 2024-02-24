package br.com.maurigvs.surveyapi.exception;

public class AnswerNotFoundException extends BadRequestException {

    public AnswerNotFoundException(Long answerId) {
        super("Answer not found by Id " + answerId);
    }
}
