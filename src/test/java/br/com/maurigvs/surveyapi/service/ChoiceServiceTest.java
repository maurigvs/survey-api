package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.maurigvs.surveyapi.mocks.MockData.mockOfChoice;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceServiceTest extends DataAdapterTest<Choice, ChoiceRepository, ChoiceService> {

    @InjectMocks
    private ChoiceService choiceService;

    @Mock
    private ChoiceRepository choiceRepository;

    @BeforeEach
    void setUp() {
        super.service = choiceService;
        super.repository = choiceRepository;
        super.entity = mockOfChoice();
    }
}
