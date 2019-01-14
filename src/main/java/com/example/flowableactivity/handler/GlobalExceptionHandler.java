package com.example.flowableactivity.handler;

import com.example.flowableactivity.model.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResultInfo<Object> handlerError(Exception e) {
        logger.error(e.getMessage(),e);
        return new ResultInfo<>(ResultInfo.codeFailure, e.getMessage(), null);
    }

}
