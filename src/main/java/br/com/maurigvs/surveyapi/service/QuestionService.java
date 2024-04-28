package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Question;
import reactor.core.publisher.Mono;

public interface QuestionService {

    Mono<Question> create(Mono<Question> questionMono);

    Mono<Question> findById(Long questionId);

    Mono<Void> deleteById(Long questionId, Long surveyId);
}
