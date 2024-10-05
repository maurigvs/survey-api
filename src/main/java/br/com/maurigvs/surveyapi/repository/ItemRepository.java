package br.com.maurigvs.surveyapi.repository;

import br.com.maurigvs.surveyapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
