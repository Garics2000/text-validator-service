package org.ie.textvalidator.service;

import org.ie.textvalidator.rules.ValidationRule;

import java.util.List;

public interface TextValidatorService {
    boolean validate(String text, List<ValidationRule> validationRules);
}
