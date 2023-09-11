package org.ie.textvalidator.rules.brackets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BracketsPairRuleTest {
    private final BracketsPairRule rule = new BracketsPairRule();

    @ParameterizedTest
    @CsvSource({
            "'()', true",
            "'(())', true",
            "'()()', true",
            "'(', false",
            "')', false",
            "')(', false",
            "'())(', false",
            "'', true", // If no braces - should not fail!
            "'(((((((((( ))))))))))', true"
    })
    void testBracketPairRule(String input, boolean expected) {
        assertEquals(expected, rule.validate(input));
    }

}
