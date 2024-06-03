package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
import br.com.maurigvs.surveyapi.dto.requests.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.dto.responses.QuestionResponse;
import br.com.maurigvs.surveyapi.dto.responses.SurveyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AggregatorService {

    Mono<SurveyResponse> createSurvey(SurveyRequest request);
    Flux<SurveyResponse> findAllSurveys();

    Mono<QuestionResponse> createQuestion(Long surveyId, QuestionRequest request);
    Mono<Void> deleteQuestion(Long surveyId, Long id);

    Mono<QuestionResponse> createChoice(Long surveyId, Long questionId, ChoiceRequest request);
    Mono<Void> deleteChoice(Long surveyId, Long questionId, Long id);

    Mono<AnswerResponse> createAnswer(Long surveyId, AnswerRequest request);
    Flux<AnswerResponse> findAllAnswers();
}
