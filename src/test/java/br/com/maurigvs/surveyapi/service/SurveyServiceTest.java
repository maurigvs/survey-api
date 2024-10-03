package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.BusinessException;
import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest extends DatabaseServiceTest<Survey, SurveyRepository, SurveyService> {

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @BeforeEach
    void setUp() {
        super.service = this.surveyService;
        super.repository = this.surveyRepository;
        super.entity = MockData.ofSurvey();
    }

    @Test
    void findQuestionInSurvey() {
        Question question = entity.getQuestions().get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        create(surveyService.findQuestionInSurvey(1L, 1L))
                .expectNext(question)
                .verifyComplete();
    }

    @Test
    void findQuestionInSurvey_expetion() {
        entity.getQuestions().clear();
        var message = "Question not found by Id 1";
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        create(surveyService.findQuestionInSurvey(1L, 1L))
                .expectErrorSatisfies(throwable -> {
                    var exception = assertInstanceOf(BusinessException.class, throwable);
                    assertEquals(message, exception.getMessage());
                })
                .verify();
    }

    @Test
    void findChoiceInQuestion() {
        entity.getQuestions().get(0).getChoices().clear();
        var message = "Choice not found by Id 1";
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        create(surveyService.findChoiceInQuestion(1L, 1L, 1L))
                .expectErrorSatisfies(throwable -> {
                    var exception = assertInstanceOf(BusinessException.class, throwable);
                    assertEquals(message, exception.getMessage());
                })
                .verify();
    }
}
