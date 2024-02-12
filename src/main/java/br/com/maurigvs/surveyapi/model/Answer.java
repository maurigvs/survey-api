package br.com.maurigvs.surveyapi.model;

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
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerItem> answerItems = new ArrayList<>();

    protected Answer() {
    }

    public Answer(Integer id, Survey survey) {
        this.id = id;
        this.survey = survey;
    }

    public Integer getId() {
        return id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public List<AnswerItem> getAnswerItems() {
        return answerItems;
    }
}
