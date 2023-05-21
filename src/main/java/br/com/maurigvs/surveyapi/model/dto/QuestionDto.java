package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto {

    private String question;

    private final List<String> choices = new ArrayList<>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }
}
