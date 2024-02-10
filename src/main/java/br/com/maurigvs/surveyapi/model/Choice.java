package br.com.maurigvs.surveyapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Choice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Choice(Integer id, String title, Question question) {
        this.id = id;
        this.title = title;
        this.question = question;
    }

    protected Choice() {}

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Question getQuestion() {
        return question;
    }
}
