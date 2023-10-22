package com.simple.calculator.api.model.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressionResponse {

    @NotBlank
    private String expression;
}
