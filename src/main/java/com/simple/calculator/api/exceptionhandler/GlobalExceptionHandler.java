package com.simple.calculator.api.exceptionhandler;

import com.simple.calculator.domain.exceptions.InvalidExpression;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.EmptyStackException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Expressão Inválida");
        problemDetail.setType(URI.create("https://filipeandrade.com/expressao-invalida"));
        var fields = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(
                objectError -> ((FieldError) objectError).getField(),
                objectError -> this.messageSource.getMessage(
                    objectError,
                    LocaleContextHolder.getLocale()
                )));
        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler({
        RuntimeException.class,
        IllegalArgumentException.class,
        EmptyStackException.class,
        UnknownFunctionOrVariableException.class,
        IllegalArgumentException.class,
        InvalidExpression.class})
    public ProblemDetail handleInvalidExpression(Exception exception,
                                                 HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(BAD_REQUEST);
        problemDetail.setTitle("Expressão Inválida");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setType(URI.create("https://filipeandrade.com/expressao-invalida"));
        problemDetail.setProperty("request", request);
        return problemDetail;
    }
}
