package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.Mocks;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
            var survey = Mocks.mockSurvey();

            // when
            surveyService.createSurvey(survey);

            // then
            verify(surveyRepository, times(1)).save(survey);
        }

        @Test
        @DisplayName("List all surveys")
        void should_ReturnList_when_FindAllSurveys(){
            // given
            Survey survey = Mocks.mockSurvey();
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