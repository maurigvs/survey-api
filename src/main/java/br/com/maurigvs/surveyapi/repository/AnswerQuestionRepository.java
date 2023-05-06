package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.AnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerQuestionRepository extends JpaRepository<AnswerQuestion, Long> {
}