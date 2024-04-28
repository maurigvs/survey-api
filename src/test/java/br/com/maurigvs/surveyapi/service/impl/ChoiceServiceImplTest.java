package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {ChoiceServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceServiceImplTest {
    
    @Autowired
    private ChoiceService choiceService;
    
    @MockBean
    private ChoiceRepository choiceRepository;

    private Choice choice;

    @BeforeEach
    void setUp() {
        choice = MockData.ofChoice();
    }

    @Test
    void should_create_choice_in_existing_question() {
        given(choiceRepository.save(any())).willReturn(choice);

        StepVerifier.create(choiceService.create(Mono.just(choice)))
                .expectNext(choice)
                .verifyComplete();

        verify(choiceRepository, times(1)).save(choice);
        verifyNoMoreInteractions(choiceRepository);
    }

    @Test
    void should_delete_choice_from_existing_question() {
        var choiceId = 1L;
        var questionId = 1L;
        var surveyId = 1L;
        given(choiceRepository.findById(anyLong())).willReturn(Optional.of(choice));

        StepVerifier.create(choiceService.deleteById(choiceId, questionId, surveyId))
                .verifyComplete();

        verify(choiceRepository, times(1)).findById(choiceId);
        verify(choiceRepository, times(1)).delete(choice);
        verifyNoMoreInteractions(choiceRepository);
    }

    @Test
    void should_throw_exception_when_choice_not_found_by_id() {
        given(choiceRepository.findById(anyLong())).willReturn(Optional.empty());

        StepVerifier.create(choiceService.deleteById(1L, 2L, 3L))
                .expectErrorMatches(throwable -> throwable instanceof ChoiceNotFoundException)
                .verify();

        verify(choiceRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(choiceRepository);
    }

    @Test
    void should_throw_exception_when_question_id_does_not_match_given_id() {
        var choiceId = 1L;
        var questionId = 5L;
        var surveyId = 1L;
        given(choiceRepository.findById(anyLong())).willReturn(Optional.of(choice));

        StepVerifier.create(choiceService.deleteById(choiceId, questionId, surveyId))
                .expectErrorMatches(throwable -> throwable instanceof QuestionNotFoundException)
                .verify();

        verify(choiceRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(choiceRepository);
    }

    @Test
    void should_throw_exception_when_survey_id_does_not_match_given_id() {
        var choiceId = 1L;
        var questionId = 1L;
        var surveyId = 2L;
        given(choiceRepository.findById(anyLong())).willReturn(Optional.of(choice));

        StepVerifier.create(choiceService.deleteById(choiceId, questionId, surveyId))
                .expectErrorMatches(throwable -> throwable instanceof SurveyNotFoundException)
                .verify();

        verify(choiceRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(choiceRepository);
    }
}