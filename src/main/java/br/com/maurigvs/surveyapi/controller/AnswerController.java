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

@Tag(name = "answer")
@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final SurveyService surveyService;

    public AnswerController(AnswerService answerService, SurveyService surveyService) {
        this.answerService = answerService;
        this.surveyService = surveyService;
    }

    @Operation(summary = "create a new answer to a survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new answer created successfully"),
            @ApiResponse(responseCode = "400", description = "survey not found")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postAnswer(@RequestBody @Valid AnswerRequest request){
        var survey = surveyService.findById(request.surveyId());
        var answer = new AnswerMapper(survey).apply(request);
        answerService.create(answer);
    }

    @Operation(summary = "list of all answers to all surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "answers listed successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AnswerResponse.class))
            })
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerResponse> findAllAnswers(){
        return answerService.findAll().stream().map(new AnswerResponseMapper()).toList();
    }
}
