package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
import br.com.maurigvs.surveyapi.dto.responses.QuestionResponse;
import br.com.maurigvs.surveyapi.service.AggregatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "choice")
@RestController
@RequestMapping("/survey/{surveyId}/question/{questionId}/choice")
@RequiredArgsConstructor
public class ChoiceController {

    private final AggregatorService service;

    @Operation(summary = "create a new choice to a question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new choice created successfully"),
            @ApiResponse(responseCode = "400", description = "question not found")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<QuestionResponse> postChoice(@PathVariable Long surveyId, @PathVariable Long questionId, @RequestBody @Valid ChoiceRequest request){
        return service.createChoice(surveyId, questionId, request);
    }

    @Operation(summary = "delete a choice from a question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "choice deleted successfully"),
            @ApiResponse(responseCode = "400", description = "survey or question not found")
    })
    @DeleteMapping("/{choiceId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteChoice(@PathVariable Long surveyId, @PathVariable Long questionId, @PathVariable Long choiceId){
        return service.deleteChoice(surveyId, questionId, choiceId);
    }
}
