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
public class Choice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "choice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerItem> answerItems = new ArrayList<>();

    public Choice(Long id, String title, Question question) {
        this.id = id;
        this.title = title;
        this.question = question;
    }

    protected Choice() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Question getQuestion() {
        return question;
    }

    public List<AnswerItem> getAnswerItems() {
        return answerItems;
    }
}
