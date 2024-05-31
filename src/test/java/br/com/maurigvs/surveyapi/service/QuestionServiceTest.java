package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = QuestionService.class)
class QuestionServiceTest extends AbstractCrudServiceTest<Question, QuestionRepository, QuestionService> {

    @Autowired
    private QuestionService service;

    @MockBean
    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        super.service = this.service;
        super.repository = this.repository;
        super.entity = MockData.ofQuestion();
    }
}
