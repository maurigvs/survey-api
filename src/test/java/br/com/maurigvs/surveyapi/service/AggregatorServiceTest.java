package br.com.maurigvs.surveyapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AggregatorServiceImpl.class)
class AggregatorServiceTest {

    @Autowired
    private AggregatorService aggregatorService;

    @MockBean
    private SurveyService surveyService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ChoiceService choiceService;

    @MockBean
    private AnswerService answerService;

    @Test
    void createSurvey() {
    }

    @Test
    void findAllSurveys() {
    }

    @Test
    void createQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void createChoice() {
    }

    @Test
    void deleteChoice() {
    }

    @Test
    void createAnswer() {
    }

    @Test
    void findAllAnswers() {
    }
}