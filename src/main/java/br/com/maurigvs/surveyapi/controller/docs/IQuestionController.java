package br.com.maurigvs.surveyapi.controller.docs;

import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "question")
public interface IQuestionController {

    @Operation(summary = "create a new question to a survey")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "new question created successfully"),
        @ApiResponse(responseCode = "400", description = "survey not found")})
    Mono<Void> postQuestion(Long surveyId, QuestionRequest request);

    @Operation(summary = "delete a question from a survey")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "question deleted successfully"),
        @ApiResponse(responseCode = "400", description = "survey not found")})
    Mono<Void> deleteQuestionById(Long surveyId, Long questionId);
}
