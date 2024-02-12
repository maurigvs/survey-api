package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.DataMock;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import br.com.maurigvs.surveyapi.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {QuestionServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestionServiceImplTest {

    @Autowired
    private QuestionService service;

    @MockBean
    private QuestionRepository repository;

    private Question question;

    @BeforeEach
    void setUp() {
        question = DataMock.ofQuestion();
    }

    @Test
    void should_create_question_in_existing_survey() {

        service.create(question);

        verify(repository, times(1)).save(question);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_delete_question_from_existing_survey() {
        var questionId = 1L;
        var surveyId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(question));

        service.deleteById(questionId, surveyId);

        verify(repository, times(1)).findById(questionId);
        verify(repository, times(1)).delete(question);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_question_not_found_by_id() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(QuestionNotFoundException.class, ()-> service.deleteById(1L, 2L));

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_survey_id_does_not_match_given_id() {
        var questionId = 1L;
        var surveyId = 2L;
        given(repository.findById(anyLong())).willReturn(Optional.of(question));

        assertThrows(SurveyNotFoundException.class, ()-> service.deleteById(questionId, surveyId));

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}