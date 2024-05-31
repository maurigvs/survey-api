package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.NotFoundException;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository repository;

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
                .switchIfEmpty(Mono.error(new NotFoundException(Choice.class, choiceId)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteById(Long choiceId, Long questionId, Long surveyId) {
        return findById(choiceId)
                .filter(choice -> choice.getQuestion().getId().equals(questionId))
                .switchIfEmpty(Mono.error(new NotFoundException(Question.class, questionId)))
                .filter(choice -> choice.getQuestion().getSurvey().getId().equals(surveyId))
                .switchIfEmpty(Mono.error(new NotFoundException(Survey.class, surveyId)))
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
