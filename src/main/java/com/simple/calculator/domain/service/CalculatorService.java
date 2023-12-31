package com.simple.calculator.domain.service;

import com.simple.calculator.domain.model.ExpressionModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor
@Service
public class CalculatorService {

    /**
     * Variable that displays the current state of the math expression
     */
    @Setter
    private ExpressionModel expression;

    /**
     * Solves the given expression by using exp4j Library and refreshes this.expression
     */
    public void solveExpression() {
        String solveThis = this.expression.getExpression().trim();

        if (solveThis.contains("%")) {
            solveThis = percentageCase(solveThis);
        }
        ExpressionBuilder builder = new ExpressionBuilder(solveThis);
        Expression solved = builder.build();
        double result = solved.evaluate();
        this.expression.setExpression(String.valueOf(result));
    }

    /**
     * changes the this.expression value acording to the user input on the
     * front-end
     *
     * @param value
     */
    public void buildExpression(ExpressionModel value) {
        this.expression = value;
    }

    /**
     * Resets the expression, now the user can write
     * any other expression
     */
    public void clear() {
        this.expression.setExpression("");
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
        Pattern pattern2 = Pattern.compile("(\\d+) \\p{Punct} (\\d+(%))");
        Matcher matcher = pattern.matcher(expression);
        Matcher matcher2 = pattern2.matcher(expression);
        if (matcher.find()) {
            return replacePercentageForMultiplication(matcher, expression);
        } else if (matcher2.find()) {
            return replacePercentageForMultiplication(matcher2, expression);
        }
        return expression;
    }

    private String replacePercentageForMultiplication(Matcher matcher, String expression) {
        float value1 = Float.parseFloat(matcher.group(1));
        float value2 = Float.parseFloat(matcher.group(2).replace("%", ""));
        return expression.replace(
            matcher.group(2),
            String.valueOf((value1 * value2 / 100))
        );
    }
}
