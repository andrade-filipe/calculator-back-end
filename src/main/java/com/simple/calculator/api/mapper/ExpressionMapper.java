package com.simple.calculator.api.mapper;

import com.simple.calculator.api.model.input.ExpressionInput;
import com.simple.calculator.api.model.response.ExpressionResponse;
import com.simple.calculator.domain.model.ExpressionModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpressionMapper {

    private final ModelMapper modelMapper;

    public ExpressionModel toEntity(ExpressionInput expressionInput){
        return modelMapper.map(expressionInput, ExpressionModel.class);
    }

    public ExpressionResponse toResponse(ExpressionModel expression){
        return modelMapper.map(expression, ExpressionResponse.class);
    }
}
