package com.simple.calculator.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressionInput {

    @NotBlank
    private String expression;
}
