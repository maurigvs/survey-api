package br.com.maurigvs.surveyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maurigvs.surveyapi.entity.dto.AnswerDto;
import br.com.maurigvs.surveyapi.entity.model.Answer;
import br.com.maurigvs.surveyapi.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService service;

    @PostMapping
    public ResponseEntity<Void> postAnswer(@RequestBody AnswerDto dto) {
        service.createAnswer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Answer>> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(service.findByEmail(email));
    }
}