package br.com.maurigvs.surveyapi.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.maurigvs.surveyapi.mocks.Mocks;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;

@SpringBootTest(classes = {SurveyService.class})
class SurveyServiceTest {

    @Autowired
    SurveyService surveyService;

    @MockBean
    SurveyRepository surveyRepository;

    @Nested
    @DisplayName("Success Tests")
    class SuccessTests{

        @Test
        @DisplayName("Save survey")
        void should_ReturnSurvey_when_CreateSurveyWithData(){
            //given
            SurveyDto surveyDto = Mocks.mockSurveyDto();
            Survey expected = Mocks.mockSurvey();
            given(surveyRepository.save(any(Survey.class))).willReturn(expected);
            // when
            Survey result = surveyService.createSurvey(surveyDto);
            // then
            assertAll(
                    () -> verify(surveyRepository, times(1)).save(any(Survey.class)),
                    () -> assertEquals(expected.getTitle(), result.getTitle()),
                    () -> assertEquals(expected.getQuestions().get(0).getTitle(),
                            result.getQuestions().get(0).getTitle()),
                    () -> assertEquals(expected.getQuestions().get(0).getChoices().get(0).getTitle(),
                            result.getQuestions().get(0).getChoices().get(0).getTitle()),
                    () -> assertEquals(expected.getQuestions().get(1).getTitle(),
                            result.getQuestions().get(1).getTitle()),
                    () -> assertEquals(expected.getQuestions().get(1).getChoices().get(0).getTitle(),
                            result.getQuestions().get(1).getChoices().get(0).getTitle())
            );
        }

        @Test
        @DisplayName("List all surveys")
        void should_ReturnList_when_FindAllSurveys(){
            // given
            Survey survey = Mocks.mockSurveyWithIds();
            List<Survey> surveyList = List.of(survey);
            given(surveyRepository.findAll()).willReturn(surveyList);
            // when
            List<Survey> result = surveyService.findAll();
            // then
            assertAll(
                    () -> verify(surveyRepository, times(1)).findAll(),
                    () -> assertEquals(1, result.size()),
                    () -> assertEquals("Sample Survey", result.get(0).getTitle()),
                    () -> assertEquals(2, result.get(0).getQuestions().size())
            );
        }
    }
}