package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.SurveyResponse;
import br.com.maurigvs.surveyapi.mapper.SurveyMapper;
import br.com.maurigvs.surveyapi.mapper.SurveyResponseMapper;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "survey")
@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "create a new survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new survey created successfully"),
            @ApiResponse(responseCode = "400", description = "survey already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postSurvey(@RequestBody Mono<SurveyRequest> requestMono){
        return requestMono
                .map(new SurveyMapper())
                .map(Mono::just)
                .flatMap(surveyService::create)
                .then();
    }

    @Operation(summary = "list of all surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "surveys listed successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SurveyResponse.class))
            })
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<SurveyResponse> findAllSurveys(){
        return surveyService.findAll().map(new SurveyResponseMapper());
    }
}
