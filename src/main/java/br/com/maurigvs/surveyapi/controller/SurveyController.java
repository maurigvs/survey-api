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
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "survey")
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Operation(summary = "create a new survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new survey created successfully"),
            @ApiResponse(responseCode = "400", description = "survey already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postSurvey(@RequestBody @Valid SurveyRequest request){
        surveyService.create(new SurveyMapper().apply(request));
    }

    @Operation(summary = "list of all surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "surveys listed successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SurveyResponse.class))
            })
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<SurveyResponse> findAllSurveys(){
        return surveyService.findAll().stream().map(new SurveyResponseMapper()).toList();
    }
}
