package br.com.maurigvs.surveyapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {

    @JsonProperty("question")
    private String title;

    @JsonProperty("choices")
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

    @Override
    public String toString() {
        return "\n  QuestionRequest{" +
                "title='" + title + '\'' +
                ",\n   choices=" + choices +
                '}';
    }
}
