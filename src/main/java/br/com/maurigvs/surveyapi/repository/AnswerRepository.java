package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
