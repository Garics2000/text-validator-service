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
        StringBuilder content = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c == '(') {
                count++;
                content.setLength(0);
            } else if (c == ')') {
                if (content.toString().trim().isEmpty()) {
                    return false;
                }
            } else if (count > 0) {
                content.append(c);
            }
        }

        return true;
    }
}
