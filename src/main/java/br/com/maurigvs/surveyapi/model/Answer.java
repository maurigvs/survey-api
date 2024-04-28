package br.com.maurigvs.surveyapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerItem> answerItems = new ArrayList<>();

    protected Answer() {
    }

    public Answer(Long id, Survey survey) {
        this.id = id;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public List<AnswerItem> getAnswerItems() {
        return answerItems;
    }
}
