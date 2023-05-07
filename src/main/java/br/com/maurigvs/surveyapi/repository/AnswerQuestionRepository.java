package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.entity.model.AnswerQuestion;

public interface AnswerQuestionRepository extends JpaRepository<AnswerQuestion, Long> {
}