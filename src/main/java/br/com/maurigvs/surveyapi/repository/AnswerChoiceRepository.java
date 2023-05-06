package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.AnswerChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerChoiceRepository extends JpaRepository<AnswerChoice, Long> {
}