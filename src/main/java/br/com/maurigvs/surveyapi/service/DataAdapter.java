package br.com.maurigvs.surveyapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@AllArgsConstructor
abstract class DataAdapter<T, R extends JpaRepository<T, Long>> {

    private R repository;

    public Mono<T> create(T t) {
        return Mono.fromSupplier(() -> repository.save(t))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<T> findById(Long id) {
        return Mono.fromSupplier(() -> repository.findById(id).orElseThrow())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<T> findAll() {
        return Flux.fromStream(repository.findAll().stream())
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteById(Long id) {
        return findById(id)
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> delete(T t) {
        return Mono.fromRunnable(() -> repository.delete(t))
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
