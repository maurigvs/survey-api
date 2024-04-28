package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.AnswerNotFoundException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import br.com.maurigvs.surveyapi.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest(classes = {AnswerServiceImpl.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerServiceImplTest {

    @Autowired
    private AnswerService service;

    @MockBean
    private AnswerRepository repository;

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = MockData.ofAnswer();
    }

    @Test
    void should_create_answer() {
        given(repository.save(any())).willReturn(answer);

        StepVerifier.create(service.create(Mono.just(answer)))
                .expectNext(answer)
                .verifyComplete();

        verify(repository, times(1)).save(answer);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_list_of_answers() {
        var answers = List.of(answer);
        given(repository.findAll()).willReturn(answers);

        StepVerifier.create(service.findAll())
                .expectNext(answer)
                .verifyComplete();

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_delete_answer_by_id() {
        var answerId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(answer));

        StepVerifier.create(service.deleteById(answerId))
                .verifyComplete();

        verify(repository, times(1)).findById(answerId);
        verify(repository, times(1)).delete(answer);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_answer_not_found_by_id() {
        var answerId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        StepVerifier.create(service.deleteById(answerId))
                .expectErrorMatches(throwable ->
                        throwable instanceof AnswerNotFoundException &&
                        throwable.getMessage().equals("Answer not found by Id 1"))
                .verify();

        verify(repository, times(1)).findById(answerId);
        verifyNoMoreInteractions(repository);
    }
}