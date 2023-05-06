package br.com.maurigvs.surveyapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Question implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Choice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private final List<AnswerQuestion> answerQuestions = new ArrayList<>();

    public Question() {
    }

    public Question(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<AnswerQuestion> getAnswerQuestions() {
        return answerQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(title, question.title) && Objects.equals(choices, question.choices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, choices);
    }

    @Override
    public String toString() {
        return "\n  Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", choices=" + choices +
                '}';
    }
}