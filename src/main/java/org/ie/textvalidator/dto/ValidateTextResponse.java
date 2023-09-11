package org.ie.textvalidator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс объекта http ответа с результатом валидации
 */
@Data
@AllArgsConstructor
public class ValidateTextResponse {
    @JsonProperty("isCorrect")
    private boolean isCorrect;
}