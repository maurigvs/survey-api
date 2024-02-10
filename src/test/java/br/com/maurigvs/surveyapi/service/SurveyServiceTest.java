package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {SurveyService.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @MockBean
    private SurveyRepository surveyRepository;

    @Test
    void should_save_survey(){
        var survey = Mock.ofSurvey();

        surveyService.createSurvey(survey);

        verify(surveyRepository, times(1)).save(survey);
    }

    @Test
    void should_return_list_of_surveys(){
        var surveys = Mock.ofSurveyList();
        given(surveyRepository.findAll()).willReturn(surveys);

        var result = surveyService.findAll();

        assertEquals(result, surveys);
        verify(surveyRepository, times(1)).findAll();
        verifyNoMoreInteractions(surveyRepository);
    }
}