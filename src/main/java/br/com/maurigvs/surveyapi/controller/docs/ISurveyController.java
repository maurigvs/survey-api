package br.com.maurigvs.surveyapi.controller.docs;

import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.SurveyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "survey")
public interface ISurveyController {

    @Operation(summary = "create a new survey")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "new survey created successfully"),
        @ApiResponse(responseCode = "400", description = "survey already exists")})
    Mono<Void> postSurvey(SurveyRequest request);

    @Operation(summary = "list of all surveys")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "surveys listed successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SurveyResponse.class))})})
    Flux<SurveyResponse> findAllSurveys();
}
