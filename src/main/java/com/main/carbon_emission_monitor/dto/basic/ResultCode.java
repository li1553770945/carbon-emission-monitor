package com.main.carbon_emission_monitor.dto.basic;


public interface ResultCode {

    /**
     * 成功
     */
    int SUCCESS = 0;
    int SYSTEM_UNKNOWN_ERROR = 5000;

    // 用户注册异常
    int PARAM_VALIDATE_ERROR = 4110;
    int USERNAME_EXIST_ERROR = 4111;

    //用户登录异常
    int USER_NOT_EXIST_OR_PASSWORD_ERROR = 4200;

    //用户权限异常
    int FORBIDDEN_ERROR = 4300;

    //参数异常
    int REQUEST_PARAM_ERROR = 4400;




}