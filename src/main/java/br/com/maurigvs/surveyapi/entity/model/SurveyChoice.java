package br.com.maurigvs.surveyapi.entity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("choice")
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private SurveyQuestion question;
    
    public SurveyChoice(String title) {
        this.title = title;
    }
}