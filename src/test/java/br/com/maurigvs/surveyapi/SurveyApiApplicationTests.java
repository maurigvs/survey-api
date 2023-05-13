package br.com.maurigvs.surveyapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.maurigvs.surveyapi.controller.AnswerController;
import br.com.maurigvs.surveyapi.controller.SurveyController;

@SpringBootTest
class SurveyApiApplicationTests {

	@Autowired
	SurveyController surveyController;

	@Autowired
	AnswerController answerController;

	@Test
	void contextLoads() {
		assertNotNull(surveyController);
		assertNotNull(answerController);
	}
}
