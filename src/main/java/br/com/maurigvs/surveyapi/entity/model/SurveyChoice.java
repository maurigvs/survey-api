package br.com.maurigvs.surveyapi.entity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "survey_choice")
public class SurveyChoice implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("choice")
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private SurveyQuestion question;

    public SurveyChoice() {
    }

    public SurveyChoice(String title) {
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

    public SurveyQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "SurveyChoice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}