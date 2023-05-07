package br.com.maurigvs.surveyapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "survey")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Survey implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyQuestion> questions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "survey")
    private final List<Answer> answers = new ArrayList<>();

   
    public Survey(String title) {
        this.title = title;
    }
}