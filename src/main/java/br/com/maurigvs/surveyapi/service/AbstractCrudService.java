package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
abstract class AbstractCrudService<T, R extends JpaRepository<T, Long>> {

    private final R repository;
    private final Class<?> entity;

    public Mono<T> create(T entity) {
        return Mono.just(entity).map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<T> findById(Long id) {
        return Mono.just(id).map(repository::findById)
                .filter(Optional::isPresent)
                .switchIfEmpty(Mono.error(new NotFoundException(entity, id)))
                .map(Optional::get)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<T> findAll(){
        return Flux.fromStream(repository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteById(Long id){
        return findById(id)
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
