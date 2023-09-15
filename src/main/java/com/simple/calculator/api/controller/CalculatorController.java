package com.simple.calculator.api.controller;

import com.simple.calculator.api.model.input.ExpressionInput;
import com.simple.calculator.domain.service.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CalculatorController {

    private CalculatorService calculatorService;

    /**
     * Requisição GET que retorna uma string com a expressão matemática
     * que está sendo escrita pelo usuário
     *
     * @return ResponseEntity<String>
     */
    @GetMapping()
    public ResponseEntity<String> getExpression() {
        var response =
                new ResponseEntity<>(
                        calculatorService.expression,
                        HttpStatus.OK
                );
        return response;
    }

    @GetMapping("/solve")
    public ResponseEntity<String> solve() {
        calculatorService.solveExpression(calculatorService.expression);
        var response = new ResponseEntity<>(calculatorService.expression, HttpStatus.OK);
        return response;
    }

    @GetMapping("/clear")
    public ResponseEntity<String> clear() {
        calculatorService.clear();
        var response = new ResponseEntity<>(calculatorService.expression, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<String> buildExpression(@RequestBody ExpressionInput expression) {
        calculatorService.buildExpression(expression.getExpression());
        var response = new ResponseEntity<>(calculatorService.expression, HttpStatus.ACCEPTED);
        return response;
    }
}
