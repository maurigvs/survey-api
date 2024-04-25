package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Choice;
import reactor.core.publisher.Mono;

public interface ChoiceService {

    Mono<Choice> create(Mono<Choice> choiceMono);

    Mono<Void> deleteById(Long choiceId, Long questionId, Long surveyId);
}
