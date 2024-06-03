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

@SpringBootTest(classes = {ChoiceController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceControllerTest {

    @Autowired
    private ChoiceController controller;

    @MockBean
    private AggregatorService service;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() {
        var questionResponse = MockData.ofQuestionResponse();
        given(service.createChoice(anyLong(), anyLong(), any())).willReturn(Mono.just(questionResponse));

        StepVerifier.create(controller.postChoice(1L ,1L, MockData.ofChoiceRequest()))
                .expectNext(questionResponse)
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() {
        given(service.deleteChoice(anyLong(), anyLong(), anyLong())).willReturn(Mono.empty());

        StepVerifier.create(controller.deleteChoice(1L,1L,2L))
                .verifyComplete();
    }

}