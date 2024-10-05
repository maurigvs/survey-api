package br.com.maurigvs.surveyapi.controller.docs;

import br.com.maurigvs.surveyapi.dto.ChoiceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "choice")
public interface IChoiceController {

    @Operation(summary = "create a new choice to a question")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "new choice created successfully"),
        @ApiResponse(responseCode = "400", description = "question not found")})
    Mono<Void> postChoice(Long surveyId, Long questionId, ChoiceRequest request);

    @Operation(summary = "delete a choice from a question")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "choice deleted successfully"),
        @ApiResponse(responseCode = "400", description = "survey or question not found")})
    Mono<Void> deleteChoiceById(Long surveyId, Long questionId, Long choiceId);
}
