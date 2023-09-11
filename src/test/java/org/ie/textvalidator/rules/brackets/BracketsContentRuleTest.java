package org.ie.textvalidator.rules.brackets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BracketsContentRuleTest {
    private final BracketsContentRule rule = new BracketsContentRule();

    @ParameterizedTest
    @CsvSource({
            "'(a)', true",
            "'(abc)', true",
            "'( a )', true",
            "'()', false",
            "'( )', false",
            "'', true",
            "'(1)', true"
    })
    void testBracketContentRule(String input, boolean expected) {
        assertEquals(expected, rule.validate(input));
    }
}