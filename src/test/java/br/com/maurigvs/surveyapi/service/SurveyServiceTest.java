package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;
import java.util.Optional;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfSurvey;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyServiceTest extends DataAdapterTest<Survey, SurveyRepository, SurveyService> {

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @BeforeEach
    void setUp() {
        super.service = surveyService;
        super.repository = surveyRepository;
        super.entity = mockOfSurvey();
    }

    @Test
    void should_return_question_when_find_question_in_survey() {
        Question expected = entity.getQuestions().get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        StepVerifier.create(service.findQuestionInSurvey(1L, 1L))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void should_throw_exception_given_question_not_found() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        StepVerifier.create(service.findQuestionInSurvey(1L, 1L))
                .expectError(NoSuchElementException.class)
                .verify();
    }

    @Test
    void should_return_choice_when_find_choice_in_question() {
        Choice expected = entity.getQuestions().get(1).getChoices().get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        StepVerifier.create(service.findChoiceInQuestion(1L, 2L, 5L))
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void should_throw_exception_given_choice_not_found() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        StepVerifier.create(service.findChoiceInQuestion(1L, 3L, 9L))
                .expectError(NoSuchElementException.class)
                .verify();
    }
}
