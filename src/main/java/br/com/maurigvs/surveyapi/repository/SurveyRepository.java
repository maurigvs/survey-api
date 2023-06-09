package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.model.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
