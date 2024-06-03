package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
import br.com.maurigvs.surveyapi.dto.requests.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.dto.responses.QuestionResponse;
import br.com.maurigvs.surveyapi.dto.responses.SurveyResponse;
import br.com.maurigvs.surveyapi.mapper.AnswerMapper;
import br.com.maurigvs.surveyapi.mapper.ChoiceMapper;
import br.com.maurigvs.surveyapi.mapper.QuestionMapper;
import br.com.maurigvs.surveyapi.mapper.SurveyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class AggregatorServiceImpl implements AggregatorService {

    private final SurveyService surveyService;
    private final QuestionService questionService;
    private final ChoiceService choiceService;
    private final AnswerService answerService;

    @Override
    public Mono<SurveyResponse> createSurvey(SurveyRequest request) {
        return Mono.just(request).map(SurveyMapper::toEntity)
                .flatMap(surveyService::create)
                .map(SurveyMapper::toResponse);
    }

    @Override
    public Flux<SurveyResponse> findAllSurveys() {
        return surveyService.findAll()
                .map(SurveyMapper::toResponse);
    }

    @Override
    public Mono<QuestionResponse> createQuestion(Long surveyId, QuestionRequest request) {
        return surveyService.findById(surveyId)
                .map(survey -> QuestionMapper.toEntity(request, survey))
                .flatMap(questionService::create)
                .map(QuestionMapper::toResponse);
    }

    @Override
    public Mono<Void> deleteQuestion(Long surveyId, Long id) {
        return questionService.deleteById(id);
    }

    @Override
    public Mono<QuestionResponse> createChoice(Long surveyId, Long questionId, ChoiceRequest request) {
        return questionService.findById(questionId)
                .map(question -> ChoiceMapper.toEntity(request, question))
                .flatMap(choiceService::create)
                .flatMap(choice -> questionService.findById(questionId).map(QuestionMapper::toResponse));
    }

    @Override
    public Mono<Void> deleteChoice(Long surveyId, Long questionId, Long id) {
        return choiceService.deleteById(id);
    }

    @Override
    public Mono<AnswerResponse> createAnswer(Long surveyId, AnswerRequest request) {
        return surveyService.findById(surveyId)
                .map(survey -> AnswerMapper.toEntity(request, survey))
                .flatMap(answerService::create)
                .map(AnswerMapper::toResponse);
    }

    @Override
    public Flux<AnswerResponse> findAllAnswers() {
        return answerService.findAll().map(AnswerMapper::toResponse);
    }
}
