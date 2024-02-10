package br.com.maurigvs.surveyapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public Question(Integer id, String title, Survey survey) {
        this.id = id;
        this.title = title;
        this.survey = survey;
    }

    protected Question() {}

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Survey getSurvey() {
        return survey;
    }
}
