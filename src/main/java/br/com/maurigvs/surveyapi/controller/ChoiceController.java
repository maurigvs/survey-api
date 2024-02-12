package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.ChoiceRequest;
import br.com.maurigvs.surveyapi.mapper.ChoiceMapper;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import br.com.maurigvs.surveyapi.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "choice")
@RestController
@RequestMapping("/survey/{surveyId}/question/{questionId}/choice")
public class ChoiceController {

    private final ChoiceService choiceService;
    private final QuestionService questionService;

    public ChoiceController(ChoiceService choiceService, QuestionService questionService) {
        this.choiceService = choiceService;
        this.questionService = questionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postChoice(@PathVariable Long surveyId,
                           @PathVariable Long questionId,
                           @RequestBody ChoiceRequest request){
        var question = questionService.findById(questionId);
        var choice = new ChoiceMapper(question).apply(request.choice());
        choiceService.create(choice);
    }

    @DeleteMapping("/{choiceId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteChoiceById(@PathVariable Long surveyId, @PathVariable Long questionId, @PathVariable Long choiceId){
        choiceService.deleteById(choiceId, questionId, surveyId);
    }
}