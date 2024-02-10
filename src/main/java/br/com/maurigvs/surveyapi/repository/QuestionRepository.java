package br.com.maurigvs.surveyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maurigvs.surveyapi.model.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
