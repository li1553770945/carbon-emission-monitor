package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ErrorCodeEnums;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseResult<String> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseResult.fail(ErrorCodeEnums.REQUEST_PARAM_ERROR.getCode(),"参数验证失败：" + ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult<String> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseResult.fail(ErrorCodeEnums.REQUEST_PARAM_ERROR.getCode(),"缺少必要的请求参数：" + ex.getParameterName());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseResult<String> handleBusinessException(BusinessException ex) {
        return ResponseResult.fail(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<String> handleBusinessException(HttpMessageNotReadableException ex) {
        return ResponseResult.fail(ErrorCodeEnums.REQUEST_PARAM_ERROR.getCode(),ErrorCodeEnums.REQUEST_PARAM_ERROR.getDesc());
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult<?> doException(Exception ex) {
        logger.error("系统错误",ex);
        return ResponseResult.fail(ErrorCodeEnums.SYSTEM_EXCEPTION.getCode(), "系统异常:"+ex);
    }
}

