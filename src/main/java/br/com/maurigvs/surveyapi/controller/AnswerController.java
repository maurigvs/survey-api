package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.docs.IAnswerController;
import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.AnswerResponse;
import br.com.maurigvs.surveyapi.mapper.EntityBuilder;
import br.com.maurigvs.surveyapi.service.AnswerService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AnswerController implements IAnswerController {

    private final AnswerService answerService;
    private final SurveyService surveyService;

    @PostMapping("/survey/{surveyId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postAnswer(@PathVariable Long surveyId,
                                 @RequestBody @Valid AnswerRequest request) {

        return surveyService.findById(surveyId)
                .map(survey -> EntityBuilder.toAnswer(request, survey))
                .flatMap(answerService::create)
                .then();
    }

    @GetMapping("/survey/answer")
    public Flux<AnswerResponse> findAllAnswers() {

        return answerService.findAll()
                .map(AnswerResponse::new);
    }
}
