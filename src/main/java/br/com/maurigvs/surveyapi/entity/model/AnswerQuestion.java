package br.com.maurigvs.surveyapi.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answer_question")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnswerQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerChoice> choices = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
