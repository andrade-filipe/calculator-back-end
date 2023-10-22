package com.simple.calculator.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

//@Data
//@SuperBuilder
//@JsonInclude(NON_NULL)
@Getter
@Setter
public class ExpressionResponse {

    private String expression;


//    protected LocalDateTime timeStamp;
//    protected int statusCode;
//    protected HttpStatus status;
//    protected String reason;
//    protected String message;
//    protected Map<?, ?> data;
}
