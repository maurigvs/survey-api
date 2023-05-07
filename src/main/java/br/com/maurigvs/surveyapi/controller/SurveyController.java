package br.com.maurigvs.surveyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maurigvs.surveyapi.entity.dto.SurveyDto;
import br.com.maurigvs.surveyapi.entity.model.Survey;
import br.com.maurigvs.surveyapi.service.SurveyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/survey")
@Slf4j
public class SurveyController {

    @Autowired
    SurveyService service;

    @PostMapping
    public ResponseEntity<Void> postSurvey(@RequestBody SurveyDto request){
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