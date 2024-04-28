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
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Choice> choices = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerItem> answerItems = new ArrayList<>();

    public Question(Long id, String title, Survey survey) {
        this.id = id;
        this.title = title;
        this.survey = survey;
    }

    protected Question() {
    }

    public Long getId() {
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

    public List<AnswerItem> getAnswerItems() {
        return answerItems;
    }
}
