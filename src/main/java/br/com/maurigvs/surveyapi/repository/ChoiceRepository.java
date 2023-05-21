package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.model.entity.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
