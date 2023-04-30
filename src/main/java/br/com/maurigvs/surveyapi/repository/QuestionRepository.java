package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
