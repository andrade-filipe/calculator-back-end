package com.simple.calculator.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import org.springframework.stereotype.Service;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class CalculatorService {

    /**
     * Variable that displays the current state of the math expression
     */
    @Getter
    private String expression = "";

    /**
     * Solves the given expression by using exp4j Library and refreshes this.expression
     * @param expression
     */
    public void solveExpression(String expression) {
        if(expression.contains("%")) {
            expression = percentageCase(expression);
        }
        ExpressionBuilder builder = new ExpressionBuilder(expression);
        Expression solved = builder.build();
        double result = solved.evaluate();
        this.expression = String.valueOf(result);
    }

    /**
     * Builds the expression one char at a time, it's designed that way because
     * of the input on the front-end, the calculator has a virtual keyboard
     *
     * @param value
     */
    public void buildExpression(String value) {
        this.expression = value;
    }

    /**
     * Resets the expression and the StringBuilder, now the user can write
     * any other expression
     */
    public void clear() {
        this.expression = "";
    }

    /**
     * Changes the Percentage char for the respective multiplication on the
     * solveExpression method
     *
     * @param expression
     * @return The same expression but changing the percentage(%) char for
     * the value already multiplied
     */
    private String percentageCase(String expression) {
        Pattern pattern = Pattern.compile("(\\d+)\\p{Punct}(\\d+(%))");
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            float value1 = Float.parseFloat(matcher.group(1));
            float value2 = Float.parseFloat(matcher.group(2).replace("%",""));
            expression = expression.replace(
                    matcher.group(2),
                    String.valueOf((value1 * value2 / 100)));
        }
        return expression;
    }

}
