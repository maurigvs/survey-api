package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.QuestionRequest;
import br.com.maurigvs.surveyapi.mapper.QuestionMapper;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "question")
@RestController
@RequestMapping("/survey/{surveyId}/question")
public class QuestionController {

    private final QuestionService questionService;
    private final SurveyService surveyService;

    public QuestionController(QuestionService questionService, SurveyService surveyService) {
        this.questionService = questionService;
        this.surveyService = surveyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postQuestion(@PathVariable Long surveyId, @RequestBody @Valid QuestionRequest request){
        var survey = surveyService.findById(surveyId);
        var question = new QuestionMapper(survey).apply(request);
        questionService.create(question);
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestionById(@PathVariable Long surveyId, @PathVariable Long questionId){
        questionService.deleteById(questionId, surveyId);
    }
}
