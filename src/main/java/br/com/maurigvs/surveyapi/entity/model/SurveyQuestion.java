package br.com.maurigvs.surveyapi.entity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "survey_question")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SurveyQuestion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("question")
    private String title;

    @JsonProperty("choices")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyChoice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public SurveyQuestion(String title) {
        this.title = title;
    }
}