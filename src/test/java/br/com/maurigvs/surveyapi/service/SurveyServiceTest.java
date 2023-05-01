package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mock.Mocks;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void should_ThrowException_when_CreateSurvey_if_EmptyTitle(){
        // given
        SurveyRequest request = new SurveyRequest();
        // when
        Executable executable = () -> service.createSurvey(request);
        // then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Survey title is required", exception.getMessage());
    }

    @Test
    void should_ThrowException_when_CreateSurvey_if_EmptyQuestions(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyQuestions();
        // when
        Executable executable = () -> service.createSurvey(request);
        // then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Surveys must have at least 1 question", exception.getMessage());
    }

    @Test
    void should_ThrowException_when_CreateQuestion_if_EmptyTitle(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyQuestionTitle();
        // when
        Executable executable = () -> service.createSurvey(request);
        // then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Question title is required", exception.getMessage());
    }

    @Test
    void should_ThrowException_when_CreateQuestion_if_EmptyChoices(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyChoices();
        // when
        Executable executable = () -> service.createSurvey(request);
        // then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Questions must have at least 1 choice", exception.getMessage());
    }

    @Test
    void should_ThrowException_when_CreateChoices_if_EmptyTitle(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyChoiceTitle();
        // when
        Executable executable = () -> service.createSurvey(request);
        // then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Choices can not be null or blank", exception.getMessage());
    }
}
