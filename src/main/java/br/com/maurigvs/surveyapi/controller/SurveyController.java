package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mapper.SurveyMapper;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Tag(name = "survey")
    @Operation(summary = "Creates a new survey")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Survey created"),
        @ApiResponse(responseCode = "400", description = "Bad Request. Required information is missing.")
    })
    @PostMapping
    public ResponseEntity<Void> postSurvey(@RequestBody @Valid SurveyDto dto){
        final var survey = new SurveyMapper().apply(dto);
        surveyService.createSurvey(survey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Tag(name = "survey")
    @Operation(summary = "List all the surveys available")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Surveys listed", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Survey.class))
        })
    })
    @GetMapping
    public ResponseEntity<List<Survey>> getSurveys(){
        List<Survey> surveyList = surveyService.findAll();
        return ResponseEntity.ok(surveyList);
    }
}