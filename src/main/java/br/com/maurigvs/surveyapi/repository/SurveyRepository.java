package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    boolean existsByTitle(String title);

    Optional<Survey> findByTitle(String title);
}
