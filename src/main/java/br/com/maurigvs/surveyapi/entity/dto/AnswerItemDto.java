package br.com.maurigvs.surveyapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
