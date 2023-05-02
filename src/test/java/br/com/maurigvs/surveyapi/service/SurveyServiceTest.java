package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mock.Mocks;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = {SurveyService.class})
class SurveyServiceTest {

    @Autowired
    SurveyService service;
    
    @MockBean
    SurveyRepository repository;

    @Test
    void should_CreateSurvey_when_RequestOk() {
        // given
        SurveyRequest request = Mocks.getSurveyRequestValid();
        Survey requestedSurvey = Mocks.getSurveyValid();
        Survey createdSurvey = Mocks.getSurveyValid();
        given(repository.save(any(Survey.class))).willReturn(createdSurvey);
        // when
        service.createSurvey(request);
        // then
        verify(repository, times(1)).save(requestedSurvey);
    }

    @Test
    void should_ListSurveys_when_RequestOk(){
        // given
        List<Survey> surveys = List.of(Mocks.getSurveyValid());
        given(repository.findAll()).willReturn(surveys);
        // when
        List<Survey> result = service.listSurveys();
        // then
        assertAll(
            () -> assertNotNull(result),
            () -> assertFalse(result.isEmpty()),
            () -> assertEquals(1, result.size())
        );
    }


    @Test
    void should_ListNothing_when_ListSurvey(){
        // given
        given(repository.findAll()).willReturn(Collections.emptyList());
        // when
        List<Survey> result = service.listSurveys();
        // then
        assertAll(
            () -> assertNotNull(result),
            () -> assertTrue(result.isEmpty()),
            () -> assertEquals(0, result.size())
        );
    }

    @Test
    void should_ThrowException_when_CreateSurvey_if_EmptyTitle(){
        // given
        SurveyRequest request = new SurveyRequest();
        // when... then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.createSurvey(request))
                .withMessage("Survey title is required");
    }

    @Test
    void should_ThrowException_when_CreateSurvey_if_EmptyQuestions(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyQuestions();
        // when... then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.createSurvey(request))
                .withMessage("Surveys must have at least 1 question");
    }

    @Test
    void should_ThrowException_when_CreateQuestion_if_EmptyTitle(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyQuestionTitle();
        // when... then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.createSurvey(request))
                .withMessage("Question title is required");
    }

    @Test
    void should_ThrowException_when_CreateQuestion_if_EmptyChoices(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyChoices();
        // when... then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.createSurvey(request))
                .withMessage("Questions must have at least 1 choice");
    }

    @Test
    void should_ThrowException_when_CreateChoices_if_EmptyTitle(){
        // given
        SurveyRequest request = Mocks.getSurveyRequestWithEmptyChoiceTitle();
        // when... then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.createSurvey(request))
                .withMessage("Choices can not be null or blank");
    }
}
