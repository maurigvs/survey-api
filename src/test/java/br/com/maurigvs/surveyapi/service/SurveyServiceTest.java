package br.com.maurigvs.surveyapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.maurigvs.surveyapi.controller.SurveyController.SurveyRequest;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;

@SpringBootTest(classes = {SurveyService.class})
class SurveyServiceTest {

    @Autowired
    SurveyService service;
    
    @MockBean
    SurveyRepository repository;

    @Test
    void should_CreateSurvey_when_RequestOk() {
        SurveyRequest request = new SurveyRequest("My Survey");

        Survey requestedSurvey = new Survey();
        requestedSurvey.setTitle("My Survey");

        Survey createdSurvey = new Survey();
        createdSurvey.setId(1L);
        createdSurvey.setTitle("My Survey");
        // given
        given(repository.save(any(Survey.class))).willReturn(createdSurvey);
        // when
        service.createSurvey(request);
        // then
        verify(repository, times(1)).save(requestedSurvey);
    }
}
