package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.AnswerNotFoundException;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import br.com.maurigvs.surveyapi.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository repository;

    @Override
    public Mono<Answer> create(Mono<Answer> answerMono) {
        return answerMono
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Answer> findById(Long answerId){
        return Mono.fromSupplier(() -> repository.findById(answerId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new AnswerNotFoundException(answerId)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Answer> findAll() {
        return Flux.fromStream(repository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteById(Long answerId) {
        return findById(answerId)
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
