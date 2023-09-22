package org.ie.textvalidator.rules.brackets;

import org.ie.textvalidator.rules.ValidationRule;

/**
 * Правило валидации содержимого скобок
 */
public class BracketsContentRule implements ValidationRule {

    /**
     * Проверяет контент между скобками (не должен быть пустым).
     *
     * @param text Текстовая строка.
     * @return boolean Результат валидации.
     */
    @Override
    public boolean validate(String text) {
        int count = 0;
        boolean hasContent = false;

        for (char c : text.toCharArray()) {
            if (c == '(') {
                count++;
                hasContent = false;
            } else if (c == ')') {
                if (count <= 0 || !hasContent) return false;
                count--;
            } else if (count > 0 && !Character.isWhitespace(c)) {
                hasContent = true;
            }
        }

        return count == 0;
    }
}