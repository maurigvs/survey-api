package br.com.maurigvs.surveyapi.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.maurigvs.surveyapi.entity.dto.SurveyDto;
import br.com.maurigvs.surveyapi.entity.dto.SurveyItemDto;
import br.com.maurigvs.surveyapi.entity.model.Survey;
import br.com.maurigvs.surveyapi.entity.model.SurveyChoice;
import br.com.maurigvs.surveyapi.entity.model.SurveyQuestion;
import br.com.maurigvs.surveyapi.service.SurveyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SurveyController.class)
@AutoConfigureMockMvc
class SurveyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SurveyService service;

    @Test
    void should_ReturnCreated_if_RequestOk_when_PostSurvey() throws Exception {
        // given
        SurveyDto dto = mockSurveyDto();
        String jsonPayload = parseToJson(dto);
        // when... then
        mockMvc.perform(post("/survey")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isCreated());
    }

    @Test
    void should_ReturnBadRequest_if_RequestEmpty_when_PostSurvey() throws Exception {
        // given no content
        // when... then
        mockMvc.perform(post("/survey"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_ReturnOK_when_GetSurvey() throws Exception {
        // given
        given(service.findAll()).willReturn(List.of(mockSurvey()));
        String jsonPayload = parseToJson(List.of(mockSurvey()));
        // when... then
        mockMvc.perform(get("/survey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonPayload));
    }

    @Test
    void should_ReturnOK_if_EmptyList_when_GetSurvey() throws Exception {
        // given
        given(service.findAll()).willReturn(Collections.emptyList());
        String jsonPayload = parseToJson(Collections.emptyList());
        // when... then
        mockMvc.perform(get("/survey"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonPayload));
    }


    private String parseToJson(Object object) throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    private SurveyDto mockSurveyDto(){
        SurveyDto dto = new SurveyDto();
        dto.setTitle("Sample Survey");

        SurveyItemDto item1 = new SurveyItemDto();
        item1.setTitle("Where does Santa Claus live?");
        item1.getChoices().addAll(List.of("Hawaii", "Finland", "Sweden", "China"));

        SurveyItemDto item2 = new SurveyItemDto();
        item2.setTitle("Were you satisfied with your Christmas presents?");
        item2.getChoices().addAll(List.of("Very satisfied", "Somewhat satisfied",
                "Neither satisfied or dissatisfied", "Dissatisfied", "Very dissatisfied"));
        dto.getItems().addAll(Arrays.asList(item1, item2));
        return dto;
    }

    private Survey mockSurvey(){
        Survey survey = new Survey("Sample Survey");
        SurveyQuestion question1 = new SurveyQuestion("Where does Santa Claus live?");
        question1.getChoices().addAll(Arrays.asList(
                new SurveyChoice("Hawaii"),
                new SurveyChoice("Finland"),
                new SurveyChoice("Sweden"),
                new SurveyChoice("China")));
        survey.getQuestions().add(question1);

        SurveyQuestion question2 = new SurveyQuestion("Were you satisfied with your Christmas presents?");
        question2.getChoices().addAll(Arrays.asList(
                new SurveyChoice("Very satisfied"),
                new SurveyChoice("Somewhat satisfied"),
                new SurveyChoice("Neither satisfied or dissatisfied"),
                new SurveyChoice("Dissatisfied"),
                new SurveyChoice("Very dissatisfied")));
        survey.getQuestions().add(question2);
        return survey;
    }
}