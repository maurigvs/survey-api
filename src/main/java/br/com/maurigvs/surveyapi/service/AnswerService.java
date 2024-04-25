package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Answer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswerService {

    Mono<Answer> create(Mono<Answer> answerMono);

    Flux<Answer> findAll();

    Mono<Void> deleteById(Long answerId);
}
