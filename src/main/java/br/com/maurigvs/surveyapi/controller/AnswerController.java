package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.AnswerRequest;
import br.com.maurigvs.surveyapi.dto.responses.AnswerResponse;
import br.com.maurigvs.surveyapi.mapper.AnswerMapper;
import br.com.maurigvs.surveyapi.mapper.AnswerResponseMapper;
import br.com.maurigvs.surveyapi.service.AnswerService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Create a new survey answer")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New answer created successfully")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postAnswer(@RequestBody @Valid AnswerRequest request){
        var survey = surveyService.findById(request.surveyId());
        var answer = new AnswerMapper(survey).apply(request);
        answerService.create(answer);
    }

    @Operation(summary = "List all answers")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Answers listed successfully")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AnswerResponse> getList(){
        return answerService.findAll().stream().map(new AnswerResponseMapper()).toList();
    }
}
