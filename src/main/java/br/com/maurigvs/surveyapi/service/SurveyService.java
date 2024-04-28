package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Survey;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SurveyService {

    Mono<Survey> create(Mono<Survey> surveyMono);

    Mono<Survey> findById(Long surveyId);

    Flux<Survey> findAll();

    Mono<Void> deleteById(Long surveyId);
}
