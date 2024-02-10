package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "QuestionRequest")
public record QuestionDto (

    @NotBlank(message = "Question title can not be blank")
    String question,

    @NotEmpty(message = "Question must have choices")
    List<String> choices

){
}