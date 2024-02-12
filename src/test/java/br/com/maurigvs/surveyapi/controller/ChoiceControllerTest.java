package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.DataMock;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import br.com.maurigvs.surveyapi.service.QuestionService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChoiceController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChoiceService choiceService;

    @MockBean
    private QuestionService questionService;

    @Test
    void should_return_Created_when_add_choice_to_existing_question() throws Exception {
        var question = DataMock.ofQuestion();
        var request = DataMock.ofChoiceRequest();
        given(questionService.findById(anyLong())).willReturn(question);

        mockMvc.perform(post("/survey/1/question/1/choice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataMock.ofJson(request)))
                .andExpect(status().isCreated());

        verify(questionService, times(1)).findById(1L);
        verify(choiceService, times(1)).create(any());
        verifyNoMoreInteractions(choiceService, questionService);
    }

    @Test
    void should_return_OK_when_delete_choice_from_existing_question() throws Exception {

        mockMvc.perform(delete("/survey/1/question/1/choice/2"))
                .andExpect(status().isOk());

        verify(choiceService, times(1)).deleteById(2L,1L, 1L);
        verifyNoMoreInteractions(choiceService);
        verifyNoInteractions(questionService);
    }

}