package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.docs.ISurveyController;
import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.mapper.EntityBuilder;
import br.com.maurigvs.surveyapi.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController implements ISurveyController {

    private final SurveyService surveyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postSurvey(@RequestBody @Valid SurveyRequest request) {

        return Mono.just(EntityBuilder.toSurvey(request))
                .flatMap(surveyService::create)
                .then();
    }

    @GetMapping
    public Flux<SurveyResponse> findAllSurveys() {

        return surveyService.findAll()
                .map(SurveyResponse::new);
    }
}
