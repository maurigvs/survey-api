package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {

    private String title;
    private List<String> choices = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getChoices() {
        return choices;
    }
}
