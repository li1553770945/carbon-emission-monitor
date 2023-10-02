package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ErrorCodeEnums;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.basic.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseResult<?> doException(Exception ex) {
        if (ex instanceof BusinessException businessException) {
            return ResponseResult.fail(businessException.getCode(), businessException.getMessage());
        } else {
            return ResponseResult.fail(ErrorCodeEnums.SYSTEM_EXCEPTION.getCode(), "系统异常");
        }
    }

}

