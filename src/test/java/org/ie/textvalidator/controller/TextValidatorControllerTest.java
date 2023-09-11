package org.ie.textvalidator.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ie.textvalidator.dto.ValidateTextRequest;
import org.ie.textvalidator.service.TextValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TextValidatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextValidatorService textValidatorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ValidateTextRequest request;

    @BeforeEach
    public void setUp() {
        request = new ValidateTextRequest();
    }

    @Test
    public void testValidInputReturnsTrue() throws Exception {
        String validInput = "Hello (team)!";
        mockValidationRule(validInput, true);

        mockMvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(true));
    }

    @Test
    public void testInvalidInputReturnsFalse() throws Exception {
        String invalidInput = "Hello (team))!";
        mockValidationRule(invalidInput, false);

        mockMvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(false));
    }

    @Test
    public void testEmptyInputReturnsBadRequest() throws Exception {
        String emptyInput = " ";
        mockValidationRule(emptyInput, false);

        mockMvc.perform(post("/api/checkBrackets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    private void mockValidationRule(String input, boolean validationResult) {
        Mockito.when(textValidatorService.validate(Mockito.eq(input), Mockito.anyList())).thenReturn(validationResult);
        request.setText(input);
    }
}