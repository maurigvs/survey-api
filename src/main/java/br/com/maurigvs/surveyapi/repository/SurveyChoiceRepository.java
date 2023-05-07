package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.entity.model.SurveyChoice;

public interface SurveyChoiceRepository extends JpaRepository<SurveyChoice, Long> {
    
}
