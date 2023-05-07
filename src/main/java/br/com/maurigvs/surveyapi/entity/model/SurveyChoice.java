package br.com.maurigvs.surveyapi.entity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "survey_choice")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SurveyChoice implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private SurveyQuestion question;

    @JsonIgnore
    @OneToMany(mappedBy = "choice")
    private final List<AnswerChoice> answerChoices = new ArrayList<>();

    
    public SurveyChoice(String title) {
        this.title = title;
    }
}