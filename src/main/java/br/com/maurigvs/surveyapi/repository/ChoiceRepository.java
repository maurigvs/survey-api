package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.model.Choice;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {
}
