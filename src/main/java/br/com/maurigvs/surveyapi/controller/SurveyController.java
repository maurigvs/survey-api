package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.QuestionDto;
import br.com.maurigvs.surveyapi.dto.SurveyDto;
import br.com.maurigvs.surveyapi.mapper.SurveyDtoMapper;
import br.com.maurigvs.surveyapi.mapper.SurveyMapper;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;
    private final QuestionService questionService;

    public SurveyController(SurveyService surveyService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    @Tag(name = "survey")
    @Operation(summary = "Creates a new survey")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Survey created"),
        @ApiResponse(responseCode = "400", description = "Bad Request. Required information is missing.")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postSurvey(@RequestBody @Valid SurveyDto dto){
        surveyService.createSurvey(new SurveyMapper().apply(dto));
    }

    @Tag(name = "survey")
    @Operation(summary = "List all the surveys available")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Surveys listed", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Survey.class))
        })
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<SurveyDto> getSurveys(){
        return surveyService.listAllSurveys().stream().map(new SurveyDtoMapper()).toList();
    }

    @PutMapping("/{surveyId}/question")
    public void putQuestionToSurvey(@PathVariable Integer surveyId,
                                    @RequestBody @Valid QuestionDto questionDto){
        var survey = surveyService.findById(surveyId);
        var question = new SurveyMapper.QuestionMapper(survey).apply(questionDto);
        questionService.createQuestion(question);
    }

    @DeleteMapping("/{surveyId}/question/{questionId}")
    public void deleteQuestionById(@PathVariable Integer surveyId,
                                   @PathVariable Integer questionId){
        questionService.deleteById(questionId, surveyId);
    }
}