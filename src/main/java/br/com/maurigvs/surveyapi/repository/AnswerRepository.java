package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.entity.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByEmail(String email);
}