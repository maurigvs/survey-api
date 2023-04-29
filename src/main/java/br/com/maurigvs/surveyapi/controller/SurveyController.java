package br.com.maurigvs.surveyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.maurigvs.surveyapi.service.SurveyService;
import lombok.Data;

@RestController
@RequestMapping("/")
public class SurveyController {

    @Autowired
    SurveyService service;

    @PostMapping
    public ResponseEntity<Void> postSurvey(@RequestBody SurveyRequest request){
        service.createSurvey(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Data
    public static class SurveyRequest {

        @JsonProperty("survey-title")
        private String title;
    }
}