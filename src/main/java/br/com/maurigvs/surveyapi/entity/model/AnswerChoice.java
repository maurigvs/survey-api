package br.com.maurigvs.surveyapi.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answer_choice")
@Getter
@Setter
@ToString
public class AnswerChoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long choiceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private AnswerQuestion question;
}
