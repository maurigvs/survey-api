package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.entity.model.AnswerChoice;

public interface AnswerChoiceRepository extends JpaRepository<AnswerChoice, Long> {
}