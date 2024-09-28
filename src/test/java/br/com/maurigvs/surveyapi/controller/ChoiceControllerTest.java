package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.mapper.DtoMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceControllerTest {

    @InjectMocks
    private ChoiceController choiceController;

    @Mock
    private SurveyService surveyService;

    @Mock
    private ChoiceService choiceService;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() {
        var question = MockData.ofQuestion();
        var choice = MockData.ofChoice();
        var request = MockData.ofChoiceRequest();
        var response = DtoMapper.mapQuestion(question);

        given(surveyService.findQuestionInSurvey(1L, 2L)).willReturn(Mono.just(question));
        given(choiceService.save(any(Choice.class))).willReturn(Mono.just(choice));

        StepVerifier.create(choiceController.postChoice(1L ,2L, request))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() {
        var choice = MockData.ofChoice();

        given(surveyService.findChoiceInQuestion(1L ,2L, 3L)).willReturn(Mono.just(choice));
        given(choiceService.delete(any(Choice.class))).willReturn(Mono.empty());

        StepVerifier.create(choiceController.deleteChoice(1L,2L,3L))
                .verifyComplete();
    }
}
