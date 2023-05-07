package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerItemRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("choices_ids")
    private final List<Long> choicesIds = new ArrayList<>();
}
