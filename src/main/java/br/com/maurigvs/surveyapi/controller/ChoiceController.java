package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.docs.IChoiceController;
import br.com.maurigvs.surveyapi.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.mapper.EntityBuilder;
import br.com.maurigvs.surveyapi.service.ChoiceService;
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
@RequestMapping("/survey/{surveyId}/question/{questionId}/choice")
@RequiredArgsConstructor
public class ChoiceController implements IChoiceController {

    private final ChoiceService choiceService;
    private final SurveyService surveyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postChoice(@PathVariable Long surveyId, @PathVariable Long questionId, @RequestBody @Valid ChoiceRequest request) {

        return surveyService.findQuestionInSurvey(surveyId, questionId)
                .map(question -> EntityBuilder.toChoice(request, question))
                .flatMap(choiceService::create)
                .then();
    }

    @DeleteMapping("/{choiceId}")
    public Mono<Void> deleteChoiceById(@PathVariable Long surveyId, @PathVariable Long questionId, @PathVariable Long choiceId) {

        return surveyService.findChoiceInQuestion(surveyId, questionId, choiceId)
                .flatMap(choiceService::delete);
    }
}
