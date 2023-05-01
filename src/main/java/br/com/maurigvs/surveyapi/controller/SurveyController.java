package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    Logger log = LoggerFactory.getLogger(SurveyController.class);

    @Autowired
    SurveyService service;

    @PostMapping
    public ResponseEntity<Void> postSurvey(@RequestBody SurveyRequest request){
        log.info("POST /api/survey request: {}", request);
        service.createSurvey(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Survey>> getSurveys(){
        List<Survey> surveys = service.listSurveys();
        log.info("GET /api/survey response: {}", surveys);
        return ResponseEntity.ok(surveys);
    }
}