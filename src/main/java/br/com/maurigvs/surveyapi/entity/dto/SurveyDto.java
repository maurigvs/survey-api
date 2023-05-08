package br.com.maurigvs.surveyapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyDto {

    @JsonProperty("survey")
    private String title;

    @JsonProperty("questions")
    private final List<SurveyItemDto> items = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SurveyItemDto> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SurveyDto{" +
                "title='" + title + '\'' +
                ", items=" + items +
                '}';
    }
}