package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.SurveyResponse;
import br.com.maurigvs.surveyapi.mapper.SurveyMapper;
import br.com.maurigvs.surveyapi.mapper.SurveyResponseMapper;
import br.com.maurigvs.surveyapi.service.SurveyService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postSurvey(@RequestBody @Valid SurveyRequest request){
        surveyService.create(new SurveyMapper().apply(request));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<SurveyResponse> getSurveys(){
        return surveyService.findAll().stream().map(new SurveyResponseMapper()).toList();
    }
}