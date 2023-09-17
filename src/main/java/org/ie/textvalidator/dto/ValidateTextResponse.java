package org.ie.textvalidator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateTextResponse {
    @JsonProperty("isCorrect")
    private boolean isCorrect;
}