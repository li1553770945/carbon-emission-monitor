package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ErrorCodeEnums;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.basic.SystemException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseResult<?> doException(Exception ex) {
        if (ex instanceof BusinessException businessException) {
            return ResponseResult.fail(businessException.getCode(), businessException.getMessage());
        } else if(ex instanceof HttpMessageNotReadableException){
            return ResponseResult.fail(ErrorCodeEnums.REQUEST_PARAM_ERROR.getCode(),ErrorCodeEnums.REQUEST_PARAM_ERROR.getDesc());

        }else {

            logger.error("",ex);
            return ResponseResult.fail(ErrorCodeEnums.SYSTEM_EXCEPTION.getCode(), "系统异常");
        }
    }

}

