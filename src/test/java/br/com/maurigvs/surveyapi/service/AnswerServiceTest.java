package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = AnswerService.class)
class AnswerServiceTest extends AbstractCrudServiceTest<Answer, AnswerRepository, AnswerService> {

    @Autowired
    private AnswerService service;

    @MockBean
    private AnswerRepository repository;

    @BeforeEach
    void setUp() {
        super.service = this.service;
        super.repository = this.repository;
        super.entity = MockData.ofAnswer();
    }
}
