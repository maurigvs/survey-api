package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.mapper.AnswerMapper;
import br.com.maurigvs.surveyapi.mapper.AnswerResponseMapper;
import br.com.maurigvs.surveyapi.service.AnswerService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "answer")
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final SurveyService surveyService;

    @Operation(summary = "create a new answer to a survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new answer created successfully"),
            @ApiResponse(responseCode = "400", description = "survey not found")
    })
    @PostMapping("/survey/{surveyId}/answer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postAnswer(@PathVariable Long surveyId, @RequestBody Mono<AnswerRequest> requestMono){
        return surveyService.findById(surveyId)
                .zipWith(requestMono)
                .map(tuple -> new AnswerMapper(tuple.getT1()).apply(tuple.getT2()))
                .map(Mono::just)
                .flatMap(answerService::create)
                .then();
    }

    @Operation(summary = "list of all answers to all surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "answers listed successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AnswerResponse.class))
            })
    })
    @GetMapping("/survey/answer")
    @ResponseStatus(HttpStatus.OK)
    public Flux<AnswerResponse> findAllAnswers(){
        return answerService.findAll().map(new AnswerResponseMapper());
    }
}
