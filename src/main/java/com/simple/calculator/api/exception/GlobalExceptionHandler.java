package com.simple.calculator.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.EmptyStackException;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler({
            RuntimeException.class,
            IllegalArgumentException.class,
            EmptyStackException.class,
            UnknownFunctionOrVariableException.class})
    public ResponseEntity<ErrorResponse> handleInvalidExpression(Exception exception,
                                                                 HttpServletRequest request){
        System.err.print("Invalid Expression " + exception.getMessage());
        return ResponseEntity.ok(
                ErrorResponse.builder()
                        .timeStamp(now())
                        .path(request.getRequestURI())
                        .statusCode(BAD_REQUEST.value())
                        .status(BAD_REQUEST)
                        .message("Invalid Expression " + exception.getMessage())
                        .build()
        );
    }
}
