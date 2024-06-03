package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.service.AggregatorService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {QuestionController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionControllerTest {

    @Autowired
    private QuestionController controller;

    @MockBean
    private AggregatorService service;

    @Test
    void should_return_Created_when_add_question_to_existing_survey() {
        var questionRequest = MockData.ofQuestionRequest();
        var questionResponse = MockData.ofQuestionResponse();
        given(service.createQuestion(anyLong(), any())).willReturn(Mono.just(questionResponse));
        
        StepVerifier.create(controller.postQuestion(1L, questionRequest))
                .expectNext(questionResponse)
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_question_from_existing_survey() {
        given(service.deleteQuestion(anyLong(), any())).willReturn(Mono.empty());

        StepVerifier.create(controller.deleteQuestion(1L, 2L))
                .verifyComplete();
    }
}