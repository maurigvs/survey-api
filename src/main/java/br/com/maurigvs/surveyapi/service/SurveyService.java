package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public class SurveyService extends DataAdapter<Survey, SurveyRepository> {

    public SurveyService(SurveyRepository repository) {
        super(repository);
    }

    public Mono<Question> findQuestionInSurvey(Long surveyId, Long questionId) {
        return findById(surveyId)
                .map(Survey::getQuestions)
                .flatMapMany(Flux::fromIterable)
                .filter(question -> question.getId().equals(questionId))
                .switchIfEmpty(Mono.error(new NoSuchElementException("Question not found")))
                .next();
    }

    public Mono<Choice> findChoiceInQuestion(Long surveyId, Long questionId, Long choiceId) {
        return findQuestionInSurvey(surveyId, questionId)
                .map(Question::getChoices)
                .flatMapMany(Flux::fromIterable)
                .filter(choice -> choice.getId().equals(choiceId))
                .switchIfEmpty(Mono.error(new NoSuchElementException("Choice not found")))
                .next();
    }
}
