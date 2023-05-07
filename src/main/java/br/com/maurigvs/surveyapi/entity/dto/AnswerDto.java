package br.com.maurigvs.surveyapi.entity.dto;

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
}
