package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SurveyService extends DatabaseService<Survey, SurveyRepository> {

    public SurveyService(SurveyRepository repository) {
        super(repository, Survey.class);
    }

    public Mono<Question> findQuestionInSurvey(Long surveyId, Long questionId){
        return findById(surveyId)
                .map(survey -> survey.getQuestions().stream()
                        .filter(question -> question.getId().equals(questionId))
                        .findFirst()
                        .orElseThrow(() -> new QuestionNotFoundException(questionId)));
    }

    public Mono<Choice> findChoiceInQuestion(Long surveyId, Long questionId, Long choiceId){
        return findQuestionInSurvey(surveyId, questionId)
                .map(question -> question.getChoices().stream()
                        .filter(choice -> choice.getId().equals(choiceId))
                        .findFirst()
                        .orElseThrow(() -> new ChoiceNotFoundException(choiceId)));
    }
}
