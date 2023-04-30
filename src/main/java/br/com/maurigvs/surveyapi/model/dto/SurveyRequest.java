package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyRequest {

    private String title;
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
