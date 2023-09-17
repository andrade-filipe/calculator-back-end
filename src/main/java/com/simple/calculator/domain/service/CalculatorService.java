package com.simple.calculator.domain.service;

import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import org.springframework.stereotype.Service;
import net.objecthunter.exp4j.ExpressionBuilder;

@RequiredArgsConstructor
@Service
public class CalculatorService {

    public String expression = "0";

    private final StringBuilder expressionStringBuilder = new StringBuilder();

    public void solveExpression(String expression) {
        ExpressionBuilder builder = new ExpressionBuilder(expression);
        Expression solved = builder.build();
        this.expression = String.valueOf(solved.evaluate());
    }


    public void buildExpression(String value) {
        this.expressionStringBuilder.append(value);
        this.expression = this.expressionStringBuilder.toString();
    }

    public void clear() {
        this.expressionStringBuilder.delete(0, this.expressionStringBuilder.length());
        this.expression = "0";
    }
}
