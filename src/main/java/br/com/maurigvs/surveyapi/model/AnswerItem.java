package br.com.maurigvs.surveyapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class AnswerItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "choice_id")
    private Choice choice;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    protected AnswerItem() {
    }

    public AnswerItem(Long id, Question question, Choice choice, Answer answer) {
        this.id = id;
        this.question = question;
        this.choice = choice;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public Choice getChoice() {
        return choice;
    }

    public Answer getAnswer() {
        return answer;
    }
}
