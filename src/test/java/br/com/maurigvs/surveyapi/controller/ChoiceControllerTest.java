package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.controller.dto.ChoiceRequest;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfChoice;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfNewChoiceRequest;
import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfQuestion;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceControllerTest {

    @InjectMocks
    private ChoiceController choiceController;

    @Mock
    private ChoiceService choiceService;

    @Mock
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() {
        ChoiceRequest request = mockOfNewChoiceRequest();
        Question question = mockOfQuestion();
        when(surveyService.findQuestionInSurvey(1L, 1L)).thenReturn(Mono.just(question));
        when(choiceService.create(any(Choice.class))).thenReturn(Mono.empty());

        StepVerifier.create(choiceController.postChoice(1L, 1L, request))
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() {
        Choice choice = mockOfChoice();
        when(surveyService.findChoiceInQuestion(1L, 1L, 2L)).thenReturn(Mono.just(choice));
        when(choiceService.delete(choice)).thenReturn(Mono.empty());

        StepVerifier.create(choiceController.deleteChoiceById(1L, 1L, 2L))
                .verifyComplete();
    }
}
