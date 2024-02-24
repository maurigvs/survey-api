package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    boolean existsByTitle(String title);
}
