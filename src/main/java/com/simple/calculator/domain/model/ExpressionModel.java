package com.simple.calculator.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExpressionModel {

    @NotBlank
    private String expression;
}
