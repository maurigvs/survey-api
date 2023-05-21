package br.com.maurigvs.surveyapi.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.maurigvs.surveyapi.model.dto.QuestionDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(name = "QuestionResponse")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Choice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public Question() {
    }

    public Question(QuestionDto dto, Survey survey) {
        this.title = dto.getQuestion();
        this.survey = survey;
        dto.getChoices().forEach(c -> this.choices.add(new Choice(c, this)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
