package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.docs.IQuestionController;
import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.mapper.EntityBuilder;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/survey/{surveyId}/question")
@RequiredArgsConstructor
public class QuestionController implements IQuestionController {

    private final QuestionService questionService;
    private final SurveyService surveyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postQuestion(@PathVariable Long surveyId, @RequestBody @Valid QuestionRequest request) {

        return surveyService.findById(surveyId)
                .map(survey -> EntityBuilder.toQuestion(request, survey))
                .flatMap(questionService::create)
                .then();
    }

    @DeleteMapping("/{questionId}")
    public Mono<Void> deleteQuestionById(@PathVariable Long surveyId, @PathVariable Long questionId) {

        return surveyService.findQuestionInSurvey(surveyId, questionId)
                .flatMap(questionService::delete);
    }
}
