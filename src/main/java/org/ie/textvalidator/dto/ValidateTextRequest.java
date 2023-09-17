package org.ie.textvalidator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidateTextRequest {
    @NotBlank(message = "Текст не может быть пустым!")
    private String text;
}
