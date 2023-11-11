package com.simple.calculator.domain.service;

import com.simple.calculator.domain.model.ExpressionModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceTest {

    @Test
    void solveExpression() {
        CalculatorService calculatorService = new CalculatorService();
        ExpressionModel expressionModel = new ExpressionModel();
        expressionModel.setExpression("1+1");
        ExpressionModel expressionModelResult = new ExpressionModel();
        expressionModelResult.setExpression("2.0");

        calculatorService.buildExpression(expressionModel);
        calculatorService.solveExpression();

        assertEquals(
            expressionModelResult.getExpression(),
            calculatorService.getExpression().getExpression());
    }

    @Test
    void buildExpression() {
        CalculatorService calculatorService = new CalculatorService();
        ExpressionModel expressionModel = new ExpressionModel();
        expressionModel.setExpression("1+1");

        calculatorService.buildExpression(expressionModel);

        assertEquals(
            expressionModel.getExpression(),
            calculatorService.getExpression().getExpression());
    }
}
