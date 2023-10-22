package com.simple.calculator.api.controller;

import com.simple.calculator.api.mapper.ExpressionMapper;
import com.simple.calculator.api.model.input.ExpressionInput;
import com.simple.calculator.api.model.response.ExpressionResponse;
import com.simple.calculator.domain.model.ExpressionModel;
import com.simple.calculator.domain.service.CalculatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CalculatorController {

    /**
     * Injecting Service Class in Controller
     */
    private final CalculatorService calculatorService;

    private final ExpressionMapper expressionMapper;

    /**
     * Requisição GET que retorna uma string com a expressão matemática
     * que está sendo escrita pelo usuário
     *
     * @return ResponseEntity<Response>
     */
    @GetMapping("/expression")
    public ResponseEntity<ExpressionResponse> getExpression() {
        var expression = this.expressionMapper.toResponse(calculatorService.getExpression());
        return ResponseEntity.ok(expression);
    }

    /**
     * Requisição GET que retorna uma string com a expressão matemática
     * Resolvida
     *
     * @return ResponseEntity<ExpressionResponse>
     */
    @GetMapping("/solve")
    public ResponseEntity<ExpressionResponse> solve() {
        this.calculatorService.solveExpression();
        var expression = this.expressionMapper.toResponse(this.calculatorService.getExpression());
        return ResponseEntity.ok(expression);
    }

    /**
     * Requisição GET que limpa a variável "expression" que está no service
     *
     * @return ResponseEntity<ExpressionResponse>
     */
    @GetMapping("/clear")
    public ResponseEntity<ExpressionResponse> clear() {
        calculatorService.clear();
        var expression = this.expressionMapper.toResponse(this.calculatorService.getExpression());
        return ResponseEntity.ok(expression);
    }

    /**
     * Gets a String from the front-end and do a concatenation into the expression
     * building the expression to be solved
     *
     * @param expression
     * @return ResponseEntity<Response>
     */
    @PostMapping("/build")
    @ResponseStatus(CREATED)
    public ExpressionModel buildExpression(@Valid @RequestBody ExpressionInput expression) {

        var buildThis = this.expressionMapper.toEntity(expression);
        calculatorService.buildExpression(buildThis);
        return calculatorService.getExpression();
    }
}
