package br.com.maurigvs.surveyapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertiesController.class)
@AutoConfigureMockMvc
public class PropertiesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetProperties() throws Exception {
        mockMvc.perform(get("/properties"))
            .andExpect(status().isOk());
    }
}
