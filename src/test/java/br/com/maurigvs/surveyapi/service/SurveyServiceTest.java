package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = SurveyService.class)
class SurveyServiceTest extends AbstractCrudServiceTest<Survey, SurveyRepository, SurveyService> {

    @Autowired
    private SurveyService service;

    @MockBean
    private SurveyRepository repository;

    @BeforeEach
    void setUp() {
        super.service = this.service;
        super.repository = this.repository;
        super.entity = MockData.ofSurvey();
    }
}
