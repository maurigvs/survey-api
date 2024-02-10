package br.com.maurigvs.surveyapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(name = "QuestionRequest")
public record QuestionDto (

    @NotBlank(message = "Question title can not be blank")
    String question,

    @NotEmpty(message = "Question must have choices")
    List<String> choices

){
}