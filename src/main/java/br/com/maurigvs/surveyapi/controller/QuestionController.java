package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.mapper.EntityMapper;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
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

@Tag(name = "question")
@RestController
@RequestMapping("/survey/{surveyId}/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final SurveyService surveyService;

    @Operation(summary = "create a new question to a survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new question created successfully"),
            @ApiResponse(responseCode = "400", description = "survey not found")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> postQuestion(@PathVariable Long surveyId, @RequestBody @Valid QuestionRequest request) {

        return surveyService.findById(surveyId)
                .map(survey -> EntityMapper.toQuestion(request, survey))
                .flatMap(questionService::create)
                .then();
    }

    @Operation(summary = "delete a question from a survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "question deleted successfully"),
            @ApiResponse(responseCode = "400", description = "survey not found")
    })
    @DeleteMapping("/{questionId}")
    public Mono<Void> deleteQuestionById(@PathVariable Long surveyId, @PathVariable Long questionId) {

        return surveyService.findQuestionInSurvey(surveyId, questionId)
                .flatMap(questionService::delete);
    }
}
