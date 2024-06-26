package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository repository;

    public ChoiceServiceImpl(ChoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Choice> create(Mono<Choice> choiceMono) {
        return choiceMono
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Choice> findById(Long choiceId){
        return Mono.fromSupplier(() -> repository.findById(choiceId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new ChoiceNotFoundException(choiceId)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteById(Long choiceId, Long questionId, Long surveyId) {
        return findById(choiceId)
                .filter(choice -> choice.getQuestion().getId().equals(questionId))
                .switchIfEmpty(Mono.error(new QuestionNotFoundException(questionId)))
                .filter(choice -> choice.getQuestion().getSurvey().getId().equals(surveyId))
                .switchIfEmpty(Mono.error(new SurveyNotFoundException(surveyId)))
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
