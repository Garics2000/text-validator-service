package org.ie.textvalidator.service;

import lombok.extern.slf4j.Slf4j;
import org.ie.textvalidator.rules.ValidationRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TextValidatorServiceImpl implements TextValidatorService {

    /**
     * Проверяет текст на соответствие заданному набору правил.
     *
     * @param text  Текстовая строка.
     * @param rules Список правил для валидации.
     * @return boolean Результат валидации текста по все заданным правилам.
     */
    @Override
    public boolean validate(String text, List<ValidationRule> rules) {
        if (text == null || rules == null) {
            log.error("Validation failed: Text or rules are null.");
            return false;
        }

        return validateRules(text, rules);
    }

    private boolean validateRules(String text, List<ValidationRule> rules) {
        for (ValidationRule rule : rules) {
            if (!validateRule(text, rule)) {
                log.warn("Text failed validation using rule: {}", rule.getClass().getSimpleName());
                return false;
            }
        }

        log.info("Text successfully validated.");
        return true;
    }

    private boolean validateRule(String text, ValidationRule rule) {
        return rule.validate(text);
    }
}

