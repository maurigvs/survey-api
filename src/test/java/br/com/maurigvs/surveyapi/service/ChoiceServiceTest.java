package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = ChoiceService.class)
class ChoiceServiceTest extends AbstractCrudServiceTest<Choice, ChoiceRepository, ChoiceService> {

    @Autowired
    private ChoiceService service;

    @MockBean
    private ChoiceRepository repository;

    @BeforeEach
    void setUp() {
        super.service = this.service;
        super.repository = this.repository;
        super.entity = MockData.ofChoice();
    }
}
