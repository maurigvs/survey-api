package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.model.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    Logger log = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    SurveyService service;

    @PostMapping
    public ResponseEntity<Void> postAnswer(@RequestBody AnswerRequest request) {
        log.info("POST /api/survey/answer request: {}", request);
        service.createAnswer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
