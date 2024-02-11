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
    private Integer id;

    private Integer questionId;

    private Integer choiceId;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public AnswerItem(Integer id, Integer questionId, Integer choiceId, Answer answer) {
        this.id = id;
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.answer = answer;
    }

    protected AnswerItem() {}

    public Integer getId() {
        return id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getChoiceId() {
        return choiceId;
    }

    public Answer getAnswer() {
        return answer;
    }
}
