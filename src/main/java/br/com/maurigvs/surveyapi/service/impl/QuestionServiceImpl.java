package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.NotFoundException;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import br.com.maurigvs.surveyapi.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;

    @Override
    public Mono<Question> create(Mono<Question> questionMono) {
        return questionMono
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Question> findById(Long questionId) {
        return Mono.fromSupplier(() -> repository.findById(questionId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(new NotFoundException(Question.class, questionId)))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteById(Long questionId, Long surveyId) {
        return findById(questionId)
                .filter(question -> question.getSurvey().getId().equals(surveyId))
                .switchIfEmpty(Mono.error(new NotFoundException(Survey.class, surveyId)))
                .doOnNext(repository::delete)
                .then()
                .subscribeOn(Schedulers.boundedElastic());
    }
}
