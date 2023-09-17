package com.simple.calculator.domain.service;

import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import org.springframework.stereotype.Service;
import net.objecthunter.exp4j.ExpressionBuilder;

@RequiredArgsConstructor
@Service
public class CalculatorService {

    /**
     * Variable that displays the current state of the math expression
     */
    public String expression = "0";

    /**
     * String builder that helps to concatenate the expression string
     */
    private final StringBuilder expressionStringBuilder = new StringBuilder();

    /**
     * Solves the given expression by using exp4j Library and refreshes this.expression
     * @param expression
     */
    public void solveExpression(String expression) {
        ExpressionBuilder builder = new ExpressionBuilder(expression);
        Expression solved = builder.build();
        this.expression = String.valueOf(solved.evaluate());
        expressionStringBuilder.delete(0, this.expressionStringBuilder.length());
        expressionStringBuilder.append(this.expression);
    }

    /**
     * Builds the expression one char at a time, it's designed that way because
     * of the input on the front-end, the calculator has a virtual keyboard
     *
     * @param value
     */
    public void buildExpression(String value) {
        this.expressionStringBuilder.append(value);
        this.expression = this.expressionStringBuilder.toString();
    }

    /**
     * Resets the expression and the StringBuilder, now the user can write
     * any other expression
     */
    public void clear() {
        this.expressionStringBuilder.delete(0, this.expressionStringBuilder.length());
        this.expression = "0";
    }
}
