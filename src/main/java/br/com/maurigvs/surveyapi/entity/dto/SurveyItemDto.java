package br.com.maurigvs.surveyapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyItemDto {

    @JsonProperty("question")
    private String title;

    @JsonProperty("choices")
    private final List<String> choices = new ArrayList<>();

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
        return "SurveyItemDto{" +
                "title='" + title + '\'' +
                ", choices=" + choices +
                '}';
    }
}
