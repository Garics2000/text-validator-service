package org.ie.textvalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Собирает все сообщения об ошибках от исключения MethodArgumentNotValidException, и возвращает их в ResponseEntity.
     *
     * @param ex выброшенное исключение
     * @return ResponseEntity с сообщениями об ошибках и http статусом ошибки.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        StringBuilder errors = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append("\n");
        });

        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
}
