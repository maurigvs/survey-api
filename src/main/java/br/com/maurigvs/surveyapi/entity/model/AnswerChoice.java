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

@Entity
@Table(name = "answer_choice")
public class AnswerChoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long choiceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private AnswerQuestion question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }

    public AnswerQuestion getQuestion() {
        return question;
    }

    public void setQuestion(AnswerQuestion question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "AnswerChoice{" +
                "id=" + id +
                ", choiceId=" + choiceId +
                '}';
    }
}
