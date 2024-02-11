package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

    @Test
    void should_create_answer() {
        var answer = Mock.ofAnswer();

        service.createAnswer(answer);

        verify(repository, times(1)).save(answer);
        verifyNoMoreInteractions(repository);
    }
}