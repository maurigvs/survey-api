package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {QuestionServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void should_create_question_in_existing_survey() {
        var question = Mock.ofSurvey().getQuestions().get(0);

        questionService.createQuestion(question);

        verify(questionRepository, times(1)).save(question);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void should_delete_question_from_existing_survey() {
        var questionId = 1;
        var surveyId = 1;
        var question = Mock.ofSurvey().getQuestions().get(0);
        given(questionRepository.findById(anyInt())).willReturn(Optional.of(question));

        questionService.deleteById(questionId, surveyId);

        verify(questionRepository, times(1)).findById(questionId);
        verify(questionRepository, times(1)).delete(question);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void should_throw_QuestionNotFoundException_when_question_not_found_by_id() {
        given(questionRepository.findById(anyInt())).willReturn(Optional.empty());

        var exception = assertThrows(QuestionNotFoundException.class,
                ()-> questionService.deleteById(1, 2));

        assertEquals("Question not found by Id 1", exception.getMessage());

        verify(questionRepository, times(1)).findById(1);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void should_throw_SurveyNotFoundException_when_survey_id_does_not_match_id_given() {
        var questionId = 1;
        var surveyId = 2;
        var question = Mock.ofSurvey().getQuestions().get(0);
        given(questionRepository.findById(anyInt())).willReturn(Optional.of(question));

        var exception = assertThrows(SurveyNotFoundException.class,
                ()-> questionService.deleteById(questionId, surveyId));

        assertEquals("Survey not found by Id 2", exception.getMessage());

        verify(questionRepository, times(1)).findById(1);
        verifyNoMoreInteractions(questionRepository);
    }
}