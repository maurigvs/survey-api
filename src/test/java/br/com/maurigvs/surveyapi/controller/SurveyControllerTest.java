package br.com.maurigvs.surveyapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.maurigvs.surveyapi.exception.StandardError;
import br.com.maurigvs.surveyapi.mocks.Mocks;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.service.SurveyService;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService surveyService;

    @Nested
    @DisplayName("Success Tests")
    class SuccessTests{

        @Test
        @DisplayName("Post Survey correctly")
        void should_ReturnCreated_when_PostSurveyWithData() throws Exception {
            SurveyDto surveyDto = Mocks.mockSurveyDto();
            String surveyAsJson = Mocks.parseToJson(surveyDto);

            mockMvc.perform(post("/survey").contentType(MediaType.APPLICATION_JSON)
                    .content(surveyAsJson)).andExpect(status().isCreated());

            verify(surveyService, times(1)).createSurvey(any(SurveyDto.class));
            verifyNoMoreInteractions(surveyService);
        }

        @Test
        @DisplayName("Get Surveys empty")
        void should_ReturnOk_when_GetSurveys() throws Exception {
            mockMvc.perform(get("/survey")).andExpect(status().isOk());
        }

        @Test
        @DisplayName("Get Surveys correctly")
        void should_ReturnSurveyList_when_GetSurveys() throws Exception {
            Survey survey = Mocks.mockSurveyWithIds();
            List<Survey> surveyList = List.of(survey);
            String listAsJson = Mocks.parseToJson(surveyList);
            given(surveyService.findAll()).willReturn(surveyList);

            mockMvc.perform(get("/survey"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(listAsJson));

            verify(surveyService, times(1)).findAll();
            verifyNoMoreInteractions(surveyService);
        }
    }

    @Nested
    @DisplayName("Failure Tests")
    class FailureTests {
        @Test
        @DisplayName("Post Survey without body")
        void should_ReturnBadRequest_when_PostSurveyWithoutBody() throws Exception {
            mockMvc.perform(post("/survey")).andExpect(status().isBadRequest());
        }



        @Test
        @DisplayName("Post Survey without title")
        void should_ReturnBadRequest_when_PostSurveyWithoutTitle() throws Exception {
            SurveyDto surveyDto = Mocks.mockSurveyDto();
            surveyDto.setSurvey("");
            String surveyAsJson = Mocks.parseToJson(surveyDto);

            StandardError error = new StandardError(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Survey title can not be blank");
            String errorAsJson = Mocks.parseToJson(error);

            mockMvc.perform(post("/survey").contentType(MediaType.APPLICATION_JSON).content(surveyAsJson))
                    .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(errorAsJson));

            verifyNoInteractions(surveyService);
        }

        @Test
        @DisplayName("Post Survey without questions")
        void should_ReturnBadRequest_when_PostSurveyWithoutQuestions() throws Exception {
            SurveyDto surveyDto = Mocks.mockSurveyDto();
            surveyDto.getQuestions().clear();
            String surveyAsJson = Mocks.parseToJson(surveyDto);

            StandardError error = new StandardError(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Survey must have questions");
            String errorAsJson = Mocks.parseToJson(error);

            mockMvc.perform(post("/survey").contentType(MediaType.APPLICATION_JSON).content(surveyAsJson))
                    .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(errorAsJson));

            verifyNoInteractions(surveyService);
        }

        @Test
        @DisplayName("Post Survey with questions without title")
        void should_ReturnBadRequest_when_PostSurveyWithQuestionWithoutTitle() throws Exception {
            SurveyDto surveyDto = Mocks.mockSurveyDto();
            surveyDto.getQuestions().get(0).setQuestion("");
            String surveyAsJson = Mocks.parseToJson(surveyDto);

            StandardError error = new StandardError(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Question title can not be blank");
            String errorAsJson = Mocks.parseToJson(error);

            mockMvc.perform(post("/survey").contentType(MediaType.APPLICATION_JSON).content(surveyAsJson))
                    .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(errorAsJson));

            verifyNoInteractions(surveyService);
        }

        @Test
        @DisplayName("Post Survey with questions without choices")
        void should_ReturnBadRequest_when_PostSurveyWithQuestionWithoutChoices() throws Exception {
            SurveyDto surveyDto = Mocks.mockSurveyDto();
            surveyDto.getQuestions().get(0).getChoices().clear();
            String surveyAsJson = Mocks.parseToJson(surveyDto);

            StandardError error = new StandardError(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Question must have choices");
            String errorAsJson = Mocks.parseToJson(error);

            mockMvc.perform(post("/survey").contentType(MediaType.APPLICATION_JSON).content(surveyAsJson))
                    .andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(errorAsJson));

            verifyNoInteractions(surveyService);
        }
    }
}