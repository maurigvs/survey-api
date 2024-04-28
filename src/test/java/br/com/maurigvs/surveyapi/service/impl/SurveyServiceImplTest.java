package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        given(repository.save(any())).willReturn(survey);

        StepVerifier.create(service.create(Mono.just(survey)))
                .expectNext(survey)
                .verifyComplete();

        //verify(repository, times(0)).existsByTitle(survey.getTitle());
        verify(repository, times(1)).save(survey);
        verifyNoMoreInteractions(repository);
    }

//    @Test
//    void should_throw_exception_when_survey_already_exists() {
//        given(repository.existsByTitle(anyString())).willReturn(true);
//
//        var exception = assertThrows(SurveyAlreadyExistsException.class, () -> service.create(Mono.just(survey)));
//        assertEquals("Survey 'Sample Survey' already exists", exception.getMessage());
//
//        verify(repository, times(1)).existsByTitle(survey.getTitle());
//        verifyNoMoreInteractions(repository);
//    }

    @Test
    void should_return_list_of_surveys(){
        var surveys = List.of(survey);
        given(repository.findAll()).willReturn(surveys);

        StepVerifier.create(service.findAll())
                .expectNext(survey)
                .verifyComplete();

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_survey_given_an_id() {
        var surveyId = 1L;
        given(repository.findById(any())).willReturn(Optional.of(survey));

        StepVerifier.create(service.findById(surveyId))
                .expectNext(survey)
                .verifyComplete();

        verify(repository, times(1)).findById(surveyId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_delete_survey_by_id() {
        var surveyId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(survey));

        StepVerifier.create(service.deleteById(surveyId))
                .verifyComplete();

        verify(repository, times(1)).findById(surveyId);
        verify(repository, times(1)).delete(survey);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_survey_not_found_by_id() {
        var surveyId = 1L;
        given(repository.findById(any())).willReturn(Optional.empty());

        StepVerifier.create(service.findById(surveyId))
                .expectErrorMatches(throwable ->
                        throwable instanceof SurveyNotFoundException &&
                        throwable.getMessage().equals("Survey not found by Id 1"))
                .verify();

        verify(repository, times(1)).findById(surveyId);
        verifyNoMoreInteractions(repository);
    }
}