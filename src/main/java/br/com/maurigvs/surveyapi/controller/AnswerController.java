package br.com.maurigvs.surveyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maurigvs.surveyapi.entity.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/answer")
@Slf4j
public class AnswerController {

    @Autowired
    SurveyService service;

    @PostMapping
    public ResponseEntity<Void> postAnswer(@RequestBody AnswerRequest request) {
        log.info("POST /api/survey/answer request: {}", request);
        service.createAnswer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}