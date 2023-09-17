package com.simple.calculator.api.controller;

import com.simple.calculator.api.model.input.ExpressionInput;
import com.simple.calculator.api.model.response.Response;
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
     * Injeção da classe de Serviço no Controlador
     */
    private final CalculatorService calculatorService;

    /**
     * Requisição GET que retorna uma string com a expressão matemática
     * que está sendo escrita pelo usuário
     *
     * @return ResponseEntity<Response>
     */
    @GetMapping("/expression")
    public ResponseEntity<Response> getExpression() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("expression", calculatorService.expression))
                        .message("Expression sucessfully retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Requisição GET que retorna uma string com a expressão matemática
     * Resolvida
     *
     * @return ResponseEntity<String>
     */
    @GetMapping("/solve")
    public ResponseEntity<Response> solve() {
        calculatorService.solveExpression(calculatorService.expression);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("expression", calculatorService.expression))
                        .message("Expression solved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Requisição GET que limpa a variável "expression" que está no service
     *
     * @return ResponseEntity<String>
     */
    @GetMapping("/clear")
    public ResponseEntity<Response> clear() {
        calculatorService.clear();
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("expression", calculatorService.expression))
                        .message("Expression cleared")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /**
     * Gets a String from the front-end and do a concatenation into the expression
     * building the expression to be solved
     *
     * @param expression
     * @return ResponseEntity<Response>
     */
    @PostMapping("/build")
    public ResponseEntity<Response> buildExpression(@Valid @RequestBody ExpressionInput expression) {
        calculatorService.buildExpression(expression.getExpression());
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("expression", calculatorService.expression))
                        .message("buildExpression called")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
}
