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

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    SurveyService service;

    @PostMapping
    public ResponseEntity<Void> postSurvey(@RequestBody SurveyDto dto){
        service.createSurvey(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Survey>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}