package br.com.maurigvs.surveyapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SurveyRequest {

    @JsonProperty("survey")
    private String title;

    @JsonProperty("questions")
    private List<QuestionRequest> questions = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }
}
