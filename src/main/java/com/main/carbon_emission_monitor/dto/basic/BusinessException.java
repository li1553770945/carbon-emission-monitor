package com.main.carbon_emission_monitor.dto.basic;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusinessException extends Exception{
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
