package org.ie.textvalidator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.ie.textvalidator.dto.ValidateTextRequest;
import org.ie.textvalidator.dto.ValidateTextResponse;
import org.ie.textvalidator.rules.ValidationRule;
import org.ie.textvalidator.rules.brackets.BracketsContentRule;
import org.ie.textvalidator.rules.brackets.BracketsPairRule;
import org.ie.textvalidator.service.TextValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TextValidatorController {

    private final TextValidatorService textValidatorService;
    private final List<ValidationRule> bracketsRules;

    @Autowired
    public TextValidatorController(TextValidatorService textValidatorService) {
        this.textValidatorService = textValidatorService;

        this.bracketsRules = Arrays.asList(
                new BracketsPairRule(),
                new BracketsContentRule()
        );
    }

    /**
     * Проверяет корректность скобок в тексте на соответствие заданному набору правил.
     *
     * @param request JSON объект с полем "text".
     * @return boolean результат проверки.
     */
    @Operation(summary = "Проверяет валидность скобок в тексте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Результат валидации текста в JSON формате.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ValidateTextResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Входное значение текста не соответствует заданным критериям и не может быть провалидировано по рулам (напр. пустое значение), JSON ответ не возвращается.")
    })
    @PostMapping("/checkBrackets")
    public ResponseEntity<ValidateTextResponse> checkBrackets(@Valid @RequestBody ValidateTextRequest request) {
        boolean result = validateText(request.getText(), bracketsRules);
        ValidateTextResponse response = new ValidateTextResponse(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean validateText(String text, List<ValidationRule> rules) {
        return textValidatorService.validate(text, rules);
    }

}

