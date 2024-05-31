package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.NotFoundException;
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
    private AnswerService answerService;

    @MockBean
    private AnswerRepository answerRepository;

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = MockData.ofAnswer();
    }

    @Test
    void should_create_answer() {
        given(answerRepository.save(any())).willReturn(answer);

        StepVerifier.create(answerService.create(Mono.just(answer)))
                .expectNext(answer)
                .verifyComplete();

        verify(answerRepository, times(1)).save(answer);
        verifyNoMoreInteractions(answerRepository);
    }

    @Test
    void should_return_list_of_answers() {
        var answers = List.of(answer);
        given(answerRepository.findAll()).willReturn(answers);

        StepVerifier.create(answerService.findAll())
                .expectNext(answer)
                .verifyComplete();

        verify(answerRepository, times(1)).findAll();
        verifyNoMoreInteractions(answerRepository);
    }

    @Test
    void should_delete_answer_by_id() {
        var answerId = 1L;
        given(answerRepository.findById(anyLong())).willReturn(Optional.of(answer));

        StepVerifier.create(answerService.deleteById(answerId))
                .verifyComplete();

        verify(answerRepository, times(1)).findById(answerId);
        verify(answerRepository, times(1)).delete(answer);
        verifyNoMoreInteractions(answerRepository);
    }

    @Test
    void should_throw_exception_when_answer_not_found_by_id() {
        var answerId = 1L;
        given(answerRepository.findById(anyLong())).willReturn(Optional.empty());

        StepVerifier.create(answerService.deleteById(answerId))
                .expectErrorMatches(throwable ->
                        throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("Answer not found by Id 1"))
                .verify();

        verify(answerRepository, times(1)).findById(answerId);
        verifyNoMoreInteractions(answerRepository);
    }
}