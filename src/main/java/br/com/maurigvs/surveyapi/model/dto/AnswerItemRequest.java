package br.com.maurigvs.surveyapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnswerItemRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("choices_ids")
    private final List<Long> choicesIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Long> getChoicesIds() {
        return choicesIds;
    }

    @Override
    public String toString() {
        return "AnswerItemRequest{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", choicesIds=" + choicesIds +
                '}';
    }
}
