package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.AnswerNotFoundException;
import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        answer = Mock.ofAnswer();
    }

    @Test
    void should_create_answer() {

        service.create(answer);

        verify(repository, times(1)).save(answer);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_list_of_answers() {
        var answers = List.of(answer);
        given(repository.findAll()).willReturn(answers);

        var result = service.findAll();

        assertSame(answers, result);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_delete_answer_by_id() {
        var answerId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(answer));

        service.deleteById(answerId);

        verify(repository, times(1)).findById(answerId);
        verify(repository, times(1)).delete(answer);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_answer_not_found_by_id() {
        var answerId = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        var exception = assertThrows(AnswerNotFoundException.class, () -> service.deleteById(answerId));
        assertEquals("Answer not found by Id 1", exception.getMessage());

        verify(repository, times(1)).findById(answerId);
        verifyNoMoreInteractions(repository);
    }
}