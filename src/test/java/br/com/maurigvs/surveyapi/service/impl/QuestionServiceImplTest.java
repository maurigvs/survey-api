package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import br.com.maurigvs.surveyapi.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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
        question = MockData.ofQuestion();
    }

    @Test
    void should_create_question_in_existing_survey() {
        given(repository.save(any())).willReturn(question);

        StepVerifier.create(service.create(Mono.just(question)))
                .expectNext(question)
                .verifyComplete();

        verify(repository, times(1)).save(question);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_delete_question_from_existing_survey() {
        var questionId = 1L;
        var surveyId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(question));

        StepVerifier.create(service.deleteById(questionId, surveyId))
                .verifyComplete();

        verify(repository, times(1)).findById(questionId);
        verify(repository, times(1)).delete(question);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_question_not_found_by_id() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        StepVerifier.create(service.deleteById(1L, 2L))
                .expectErrorMatches(throwable -> throwable instanceof QuestionNotFoundException)
                .verify();

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_survey_id_does_not_match_given_id() {
        var questionId = 1L;
        var surveyId = 2L;
        given(repository.findById(anyLong())).willReturn(Optional.of(question));

        StepVerifier.create(service.deleteById(questionId, surveyId))
                .expectErrorMatches(throwable -> throwable instanceof SurveyNotFoundException)
                .verify();

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}