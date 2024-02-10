package br.com.maurigvs.surveyapi.model.entity;

import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(name = "SurveyResponse")
public class Survey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Question> questions = new ArrayList<>();

    public Survey() {
    }

    public Survey(SurveyDto dto) {
        this.title = dto.survey();
        dto.questions().forEach(q -> this.questions.add(new Question(q, this)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
