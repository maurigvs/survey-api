package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mock.Mocks;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {SurveyService.class})
class SurveyServiceTest {

    @Autowired
    SurveyService service;
    
    @MockBean
    SurveyRepository repository;

    @Test
    void should_CreateSurvey_when_RequestOk() {
        SurveyRequest request = Mocks.getSurveyRequestValid();
        Survey requestedSurvey = Mocks.getSurveyValid();
        Survey createdSurvey = Mocks.getSurveyValid();
        // given
        given(repository.save(any(Survey.class))).willReturn(createdSurvey);
        // when
        service.createSurvey(request);
        // then
        verify(repository, times(1)).save(requestedSurvey);
    }
}
