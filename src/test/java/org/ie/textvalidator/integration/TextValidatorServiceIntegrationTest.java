package org.ie.textvalidator.integration;

import org.ie.textvalidator.rules.ValidationRule;
import org.ie.textvalidator.rules.brackets.BracketsContentRule;
import org.ie.textvalidator.rules.brackets.BracketsPairRule;
import org.ie.textvalidator.service.TextValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TextValidatorServiceIntegrationTest {

    @Autowired
    private TextValidatorService textValidatorService;

    private List<ValidationRule> rules;

    @BeforeEach
    public void setUp() {
        rules = Arrays.asList(new BracketsPairRule(), new BracketsContentRule());
    }

    @Test
    public void testValidInputNoBracketsShouldReturnTrue() {
        assertTrue(textValidatorService.validate("hello world!", rules));
    }

    @Test
    public void testValidInputWithPairedNotEmptyBracketsShouldReturnTrue() {
        assertTrue(textValidatorService.validate("hello (world)!", rules));
    }

    @Test
    public void testValidInputNotPairedBracketsShouldReturnFalse() {
        assertFalse(textValidatorService.validate("Привет)", rules));
    }

    @Test
    public void testValidInputEmptyBracketsShouldReturnFalse() {
        assertFalse(textValidatorService.validate("Привет()", rules));
    }
}