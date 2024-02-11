package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.mapper.QuestionMapper;
import br.com.maurigvs.surveyapi.service.QuestionService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "question")
@RestController
@RequestMapping("/survey/{surveyId}/question")
public class QuestionController {

    private final SurveyService surveyService;
    private final QuestionService questionService;

    public QuestionController(SurveyService surveyService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    @Operation(summary = "Add a new Question to a existing Survey")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "New question created successfully")})
    @PutMapping
    public void putQuestionToSurvey(@Parameter(description = "Id of the Survey do the updated")
                                    @PathVariable Integer surveyId,
                                    @RequestBody @Valid QuestionRequest request){
        var survey = surveyService.findById(surveyId);
        var question = new QuestionMapper(survey).apply(request);
        questionService.createQuestion(question);
    }

    @Operation(summary = "Delete a question from a existing survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The question was deleted successfully")
    })
    @DeleteMapping("/{questionId}")
    public void deleteQuestionById(@Parameter(description = "Id of the survey parent of question")
                                   @PathVariable Integer surveyId,
                                   @Parameter(description = "Id of the question do be deleted")
                                   @PathVariable Integer questionId){
        questionService.deleteById(questionId, surveyId);
    }
}
