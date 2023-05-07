package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    
}
