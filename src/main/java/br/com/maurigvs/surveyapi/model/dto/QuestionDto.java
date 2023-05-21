package br.com.maurigvs.surveyapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "QuestionRequest")
public class QuestionDto {

    @NotBlank(message = "Question title can not be blank")
    private String question;

    @NotEmpty(message = "Question must have choices")
    private final List<String> choices = new ArrayList<>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }
}
