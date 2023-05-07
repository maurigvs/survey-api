package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.entity.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}