package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(name = "Question")
public record QuestionRequest(

        @NotBlank(message = "Question title can not be blank")
        String question,

        @NotEmpty(message = "Question must have choices")
        List<String> choices
){
}