package org.ie.textvalidator.rules.brackets;

import org.ie.textvalidator.rules.ValidationRule;

/**
 * Правило валидации парности скобок.
 */
public class BracketsPairRule implements ValidationRule {

    /**
     * Проверяет вхождения символов скобок "()" на сбалансированность.
     *
     * @param text Текстовая строка.
     * @return boolean Результат валидации.
     */
    @Override
    public boolean validate(String text) {
        int count = 0;

        for (char c : text.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }

        return count == 0;
    }
}