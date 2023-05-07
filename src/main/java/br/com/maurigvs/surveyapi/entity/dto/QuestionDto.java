package br.com.maurigvs.surveyapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionDto {

    @JsonProperty("question")
    private String title;

    @JsonProperty("choices")
    private final List<String> choices = new ArrayList<>();
}
