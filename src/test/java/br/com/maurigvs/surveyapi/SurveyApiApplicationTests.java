package br.com.maurigvs.surveyapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.maurigvs.surveyapi.controller.PropertiesController;

@SpringBootTest
class SurveyApiApplicationTests {

	@Autowired
	private PropertiesController propertiesController;

	@Test
	void contextLoads() {
		assertNotNull(propertiesController);
	}
}
