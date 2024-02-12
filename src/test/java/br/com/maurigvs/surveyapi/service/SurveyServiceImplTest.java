package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {SurveyServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyServiceImplTest {

    @Autowired
    private SurveyService surveyService;

    @MockBean
    private SurveyRepository surveyRepository;

    @Test
    void should_save_survey(){
        var survey = Mock.ofSurvey();
        given(surveyRepository.existsByTitle(anyString())).willReturn(false);

        surveyService.createSurvey(survey);

        verify(surveyRepository, times(1)).existsByTitle(survey.getTitle());
        verify(surveyRepository, times(1)).save(survey);
        verifyNoMoreInteractions(surveyRepository);
    }

    @Test
    void should_throw_SurveyAlreadyExistsException_when_survey_already_exists() {
        var survey = Mock.ofSurvey();
        var messageExcepted = "Survey 'Sample Survey' already exists";
        given(surveyRepository.existsByTitle(anyString())).willReturn(true);

        var exception = assertThrows(SurveyAlreadyExistsException.class, () -> surveyService.createSurvey(survey));
        assertEquals(messageExcepted, exception.getMessage());

        verify(surveyRepository, times(1)).existsByTitle(survey.getTitle());
        verifyNoMoreInteractions(surveyRepository);
    }

    @Test
    void should_return_list_of_surveys(){
        var surveys = Mock.ofSurveyList();
        given(surveyRepository.findAll()).willReturn(surveys);

        var result = surveyService.listAllSurveys();

        assertEquals(result, surveys);
        verify(surveyRepository, times(1)).findAll();
        verifyNoMoreInteractions(surveyRepository);
    }

    @Test
    void should_return_Survey_given_an_Id() {
        var surveyId = 1;
        var survey = Mock.ofSurvey();
        given(surveyRepository.findById(any())).willReturn(Optional.of(survey));

        var result = surveyService.findById(surveyId);

        verify(surveyRepository, times(1)).findById(surveyId);
        verifyNoMoreInteractions(surveyRepository);
        assertSame(survey, result);
    }

    @Test
    void should_throw_SurveyNotFoundException_when_survey_not_found_by_id() {
        var surveyId = 1;
        given(surveyRepository.findById(any())).willReturn(Optional.empty());

        var exception = assertThrows(SurveyNotFoundException.class, () -> surveyService.findById(surveyId));
        assertEquals("Survey not found by Id 1", exception.getMessage());

        verify(surveyRepository, times(1)).findById(surveyId);
        verifyNoMoreInteractions(surveyRepository);
    }
}