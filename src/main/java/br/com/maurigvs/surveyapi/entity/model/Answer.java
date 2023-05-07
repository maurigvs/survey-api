package br.com.maurigvs.surveyapi.entity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answer")
@Getter
@Setter
@ToString
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Long surveyId;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerQuestion> questions = new ArrayList<>();
}
