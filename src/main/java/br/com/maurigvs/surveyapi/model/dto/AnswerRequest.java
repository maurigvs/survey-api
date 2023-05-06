package br.com.maurigvs.surveyapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnswerRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("survey_id")
    private Long surveyId;

    @JsonProperty("answers")
    private final List<AnswerItemRequest> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public List<AnswerItemRequest> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "AnswerRequest{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", surveyId=" + surveyId +
                ", items=" + items +
                '}';
    }
}
