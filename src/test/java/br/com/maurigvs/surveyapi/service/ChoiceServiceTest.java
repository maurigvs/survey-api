package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.mocks.MockData;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChoiceServiceTest extends DatabaseServiceTest<Choice, ChoiceRepository, ChoiceService> {

    @InjectMocks
    private ChoiceService choiceService;

    @Mock
    private ChoiceRepository choiceRepository;

    @BeforeEach
    void setUp() {
        super.service = this.choiceService;
        super.repository = this.choiceRepository;
        super.entity = MockData.ofChoice();
    }
}
