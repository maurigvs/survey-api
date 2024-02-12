package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Choice;

public interface ChoiceService {

    void create(Choice choice);

    void deleteById(Long choiceId, Long questionId, Long surveyId);

}
