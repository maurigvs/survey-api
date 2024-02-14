package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import br.com.maurigvs.surveyapi.service.impl.ChoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {ChoiceServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceServiceImplTest {
    
    @Autowired
    private ChoiceService service;
    
    @MockBean
    private ChoiceRepository repository;

    private Choice choice;

    @BeforeEach
    void setUp() {
        choice = MockData.ofChoice();
    }

    @Test
    void should_create_choice_in_existing_question() {

        service.create(choice);

        verify(repository, times(1)).save(choice);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_delete_choice_from_existing_question() {
        var choiceId = 1L;
        var questionId = 1L;
        var surveyId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(choice));

        service.deleteById(choiceId, questionId, surveyId);

        verify(repository, times(1)).findById(choiceId);
        verify(repository, times(1)).delete(choice);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_choice_not_found_by_id() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ChoiceNotFoundException.class, ()-> service.deleteById(1L, 2L, 3L));

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_question_id_does_not_match_given_id() {
        var choiceId = 1L;
        var questionId = 5L;
        var surveyId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(choice));

        assertThrows(QuestionNotFoundException.class, ()-> service.deleteById(choiceId, questionId, surveyId));

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_survey_id_does_not_match_given_id() {
        var choiceId = 1L;
        var questionId = 1L;
        var surveyId = 2L;
        given(repository.findById(anyLong())).willReturn(Optional.of(choice));

        assertThrows(SurveyNotFoundException.class, ()-> service.deleteById(choiceId, questionId, surveyId));

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}