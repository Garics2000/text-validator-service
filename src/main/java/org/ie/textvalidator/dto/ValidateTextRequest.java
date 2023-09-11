package org.ie.textvalidator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Класс объекта http запроса
 */
@Data
public class ValidateTextRequest {

    /**
     * Текстовое поле запроса.
     * @NotBlank значение не может быть пустым или null
     *
     */
    @NotBlank(message = "Текст не может быть пустым!")
    private String text;
}
