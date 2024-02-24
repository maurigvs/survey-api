package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.AnswerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerItemRepository extends JpaRepository<AnswerItem, Long> {
}
