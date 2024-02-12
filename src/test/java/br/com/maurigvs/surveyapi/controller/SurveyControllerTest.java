package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.SurveyRequest;
import br.com.maurigvs.surveyapi.dto.responses.ErrorResponse;
import br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException;
import br.com.maurigvs.surveyapi.mocks.DataMock;
import br.com.maurigvs.surveyapi.service.SurveyService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    void should_return_Created_when_post_survey() throws Exception {
        var request = DataMock.ofSurveyRequest();

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataMock.ofJson(request)))
                .andExpect(status().isCreated());

        verify(surveyService, times(1)).create(any());
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_return_OK_when_get_survey_list() throws Exception {
        var surveys = DataMock.ofSurveyList();
        var response = DataMock.ofSurveyResponseList();
        given(surveyService.findAll()).willReturn(surveys);

        mockMvc.perform(get("/survey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(DataMock.ofJson(response)));

        verify(surveyService, times(1)).findAll();
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_return_Bad_Request_when_MethodArgumentNotValidException_is_thrown() throws Exception {
        var request = new SurveyRequest("", DataMock.ofSurveyRequest().questions());
        var response = new ErrorResponse("Bad Request", List.of("The field [survey] must not be blank"));

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataMock.ofJson(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(DataMock.ofJson(response)));

        verifyNoInteractions(surveyService);
    }

    @Test
    void should_return_Bad_Request_when_BadRequestException_is_thrown() throws Exception {
        var request = DataMock.ofSurveyRequest();
        var response = new ErrorResponse("Bad Request","Survey 'Sample Survey' already exists");
        willThrow(new SurveyAlreadyExistsException(request.survey())).given(surveyService).create(any());

        mockMvc.perform(post("/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataMock.ofJson(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(DataMock.ofJson(response)));

        verify(surveyService, times(1)).create(any());
        verifyNoMoreInteractions(surveyService);
    }

    @Test
    void should_return_Not_Found_when_NoResourceFoundException_is_thrown() throws Exception {
        var response = new ErrorResponse("Not Found",
                "Endpoint inexistent or missing required parameters: POST /inexistent");

        mockMvc.perform(post("/inexistent"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(DataMock.ofJson(response)));

        verifyNoInteractions(surveyService);
    }
}