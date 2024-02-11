package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.mapper.AnswerMapper;
import br.com.maurigvs.surveyapi.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postAnswer(@RequestBody @Valid AnswerRequest request){
        var answer = new AnswerMapper().apply(request);
        service.createAnswer(answer);
    }
}
