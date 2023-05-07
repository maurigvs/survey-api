package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.SurveyChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyChoiceRepository extends JpaRepository<SurveyChoice, Long> {
    
}
