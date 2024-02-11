package br.com.maurigvs.surveyapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer surveyId;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerItem> items = new ArrayList<>();

    public Answer(Integer id, Integer surveyId) {
        this.id = id;
        this.surveyId = surveyId;
    }

    protected Answer() {}

    public Integer getId() {
        return id;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public List<AnswerItem> getItems() {
        return items;
    }
}
