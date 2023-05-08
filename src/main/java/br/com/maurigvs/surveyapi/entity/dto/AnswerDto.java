package br.com.maurigvs.surveyapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerDto {

    @JsonIgnore
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("survey_id")
    private Long surveyId;

    @JsonProperty("survey")
    private Long surveyTitle;

    @JsonProperty("answers")
    private final List<AnswerItemDto> items = new ArrayList<>();

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

    public Long getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(Long surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public List<AnswerItemDto> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", surveyId=" + surveyId +
                ", surveyTitle=" + surveyTitle +
                ", items=" + items +
                '}';
    }
}
