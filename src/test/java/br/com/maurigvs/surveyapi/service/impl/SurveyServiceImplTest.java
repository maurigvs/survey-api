package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import br.com.maurigvs.surveyapi.service.SurveyService;
import br.com.maurigvs.surveyapi.service.impl.SurveyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {SurveyServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyServiceImplTest {

    @Autowired
    private SurveyService service;

    @MockBean
    private SurveyRepository repository;

    private Survey survey;

    @BeforeEach
    void setUp() {
        survey = MockData.ofSurvey();
    }

    @Test
    void should_create_survey(){
        given(repository.existsByTitle(anyString())).willReturn(false);

        service.create(survey);

        verify(repository, times(1)).existsByTitle(survey.getTitle());
        verify(repository, times(1)).save(survey);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_survey_already_exists() {
        given(repository.existsByTitle(anyString())).willReturn(true);

        var exception = assertThrows(SurveyAlreadyExistsException.class, () -> service.create(survey));
        assertEquals("Survey 'Sample Survey' already exists", exception.getMessage());

        verify(repository, times(1)).existsByTitle(survey.getTitle());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_list_of_surveys(){
        var surveys = List.of(survey);
        given(repository.findAll()).willReturn(surveys);

        var result = service.findAll();

        assertSame(result, surveys);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_survey_given_an_id() {
        var surveyId = 1L;
        given(repository.findById(any())).willReturn(Optional.of(survey));

        var result = service.findById(surveyId);

        verify(repository, times(1)).findById(surveyId);
        verifyNoMoreInteractions(repository);
        assertSame(survey, result);
    }

    @Test
    void should_delete_survey_by_id() {
        var surveyId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(survey));

        service.deleteById(surveyId);

        verify(repository, times(1)).findById(surveyId);
        verify(repository, times(1)).delete(survey);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_survey_not_found_by_id() {
        var surveyId = 1L;
        given(repository.findById(any())).willReturn(Optional.empty());

        var exception = assertThrows(SurveyNotFoundException.class, () -> service.findById(surveyId));
        assertEquals("Survey not found by Id 1", exception.getMessage());

        verify(repository, times(1)).findById(surveyId);
        verifyNoMoreInteractions(repository);
    }
}