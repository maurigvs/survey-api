package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.mocks.Mock;
import br.com.maurigvs.surveyapi.service.AnswerService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService service;

    @Test
    void should_return_Created_when_post_answer() throws Exception {
        var request = Mock.ofAnswerRequest();

        mockMvc.perform(post("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mock.ofJson(request)))
                .andExpect(status().isCreated());

        verify(service, times(1)).createAnswer(any());
        verifyNoMoreInteractions(service);
    }
}