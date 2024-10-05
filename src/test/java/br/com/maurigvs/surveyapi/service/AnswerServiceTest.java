package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfAnswer;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerServiceTest extends DataAdapterTest<Answer, AnswerRepository, AnswerService> {

    @InjectMocks
    private AnswerService answerService;

    @Mock
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        super.service = answerService;
        super.repository = answerRepository;
        super.entity = mockOfAnswer();
    }
}
