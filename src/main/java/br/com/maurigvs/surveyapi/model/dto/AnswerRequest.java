package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerRequest {

    @JsonIgnore
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("survey_id")
    private Long surveyId;

    @JsonProperty("answers")
    private final List<AnswerItemRequest> items = new ArrayList<>();
}
