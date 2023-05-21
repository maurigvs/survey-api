package br.com.maurigvs.surveyapi.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.maurigvs.surveyapi.CustomMocks;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;

@SpringBootTest(classes = {SurveyService.class})
class SurveyServiceTest {

    @Autowired
    SurveyService surveyService;

    @MockBean
    SurveyRepository surveyRepository;

    @Test
    void should_ReturnSurvey_when_CreateSurveyWithData(){
        //given
        SurveyDto surveyDto = CustomMocks.mockSurveyDto();
        Survey expected = CustomMocks.mockSurvey();
        given(surveyRepository.save(any(Survey.class))).willReturn(expected);
        // when
        Survey result = surveyService.createSurvey(surveyDto);
        // then
        assertAll(
            () -> verify(surveyRepository, times(1)).save(any(Survey.class)),
            () -> assertEquals(expected.getTitle(), result.getTitle()),
            () -> assertEquals(expected.getQuestionList().get(0).getTitle(),
                    result.getQuestionList().get(0).getTitle()),
            () -> assertEquals(expected.getQuestionList().get(0).getChoiceList().get(0).getTitle(),
                    result.getQuestionList().get(0).getChoiceList().get(0).getTitle()),
            () -> assertEquals(expected.getQuestionList().get(1).getTitle(),
                    result.getQuestionList().get(1).getTitle()),
            () -> assertEquals(expected.getQuestionList().get(1).getChoiceList().get(0).getTitle(),
                    result.getQuestionList().get(1).getChoiceList().get(0).getTitle())
        );
    }


}