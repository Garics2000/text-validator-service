package org.ie.textvalidator.service;

import org.ie.textvalidator.rules.ValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextValidatorServiceTest {

    private TextValidatorService textValidatorService;
    private ValidationRule mockGeneralRule;

    @BeforeEach
    public void setup() {
        mockGeneralRule = mock(ValidationRule.class);
        textValidatorService = new TextValidatorServiceImpl();
    }

    @Test
    public void validInputValidContentShouldReturnTrue() {
        String testText = "valid";
        when(mockGeneralRule.validate(testText)).thenReturn(true);
        
        boolean result = textValidatorService.validate(testText, Collections.singletonList(mockGeneralRule));

        assertTrue(result);
    }

    @Test
    public void validInputIValidContentShouldReturnFalse() {
        String testText = "valid input, invalid content ())";
        when(mockGeneralRule.validate(testText)).thenReturn(false);

        boolean result = textValidatorService.validate(testText, Collections.singletonList(mockGeneralRule));

        assertFalse(result);
    }
}