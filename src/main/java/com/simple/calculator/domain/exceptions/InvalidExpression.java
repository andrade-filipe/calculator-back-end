package com.simple.calculator.domain.exceptions;

public class InvalidExpression extends RuntimeException {
    public InvalidExpression(String message) {
        super(message);
    }
}
