package br.com.maurigvs.surveyapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerItemDto {

    @JsonIgnore
    private Long id;

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("question")
    private String questionTitle;

    @JsonProperty("choices_ids")
    private final List<Long> choicesIds = new ArrayList<>();

    @JsonProperty("choices")
    private final List<String> choicesTitles = new ArrayList<>();

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

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public List<Long> getChoicesIds() {
        return choicesIds;
    }

    public List<String> getChoicesTitles() {
        return choicesTitles;
    }

    @Override
    public String toString() {
        return "AnswerItemDto{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", choicesIds=" + choicesIds +
                ", choicesTitles=" + choicesTitles +
                '}';
    }
}
