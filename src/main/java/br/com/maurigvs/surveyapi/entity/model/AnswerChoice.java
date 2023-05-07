package br.com.maurigvs.surveyapi.entity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "answer_choice")
@Getter
@Setter
@ToString
public class AnswerChoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "choice_id")
    private SurveyChoice choice;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private AnswerQuestion question;
}
