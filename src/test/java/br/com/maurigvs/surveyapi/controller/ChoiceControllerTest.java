package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import br.com.maurigvs.surveyapi.service.QuestionService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {ChoiceController.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceControllerTest {

    @Autowired
    private ChoiceController choiceController;

    @MockBean
    private ChoiceService choiceService;

    @MockBean
    private QuestionService questionService;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() throws Exception {
        var choiceMono = Mono.just(MockData.ofChoice());
        var questionMono = Mono.just(MockData.ofQuestion());
        var choiceRequest = MockData.ofChoiceRequest();
        given(questionService.findById(1L)).willReturn(questionMono);
        given(choiceService.create(any())).willReturn(choiceMono);

        StepVerifier.create(choiceController.postChoice(1L ,1L, choiceRequest))
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() throws Exception {
        given(choiceService.deleteById(2L)).willReturn(Mono.empty());

        StepVerifier.create(choiceController.deleteChoiceById(1L,1L,2L))
                .verifyComplete();

        verify(choiceService, times(1)).deleteById(2L);
        verifyNoMoreInteractions(choiceService);
        verifyNoInteractions(questionService);
    }

}