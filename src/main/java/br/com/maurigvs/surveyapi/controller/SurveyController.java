package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @PostMapping
    public ResponseEntity<Void> postSurvey(@RequestBody SurveyRequest request){
        surveyService.createSurvey(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}