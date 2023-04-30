package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    
}
