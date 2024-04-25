package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import br.com.maurigvs.surveyapi.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;

    @Override
    public Mono<Survey> create(Mono<Survey> surveyMono) {
        return surveyMono
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Survey> findById(Long surveyId) {
        return Mono.fromSupplier(() -> repository.findById(surveyId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new SurveyNotFoundException(surveyId)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Survey> findAll() {
        return Flux.fromStream(repository.findAll().stream());
    }

    @Override
    public Mono<Void> deleteById(Long surveyId) {
        return findById(surveyId)
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }

}
