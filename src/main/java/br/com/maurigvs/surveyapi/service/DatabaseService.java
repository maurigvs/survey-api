package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.BusinessException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class DatabaseService<T, R extends JpaRepository<T, Long>> {

    static final String ENTITY_NOT_FOUND = "%s not found by Id %s";

    private final R repository;
    private final Class<?> entity;

    public Mono<T> save(T entity) {
        return Mono.fromSupplier(() -> repository.save(entity))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<T> findById(Long id) {
        return Mono.fromSupplier(() -> repository.findById(id))
                .filter(Optional::isPresent)
                .switchIfEmpty(Mono.error(new BusinessException(ENTITY_NOT_FOUND.formatted(entity.getSimpleName(), id))))
                .map(Optional::get)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<T> findAll(){
        return Flux.fromStream(repository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> delete(T entity){
        return Mono.just(entity)
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
