package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ChoiceService extends DatabaseService<Choice, ChoiceRepository> {

    public ChoiceService(ChoiceRepository repository) {
        super(repository, Choice.class);
    }
}
