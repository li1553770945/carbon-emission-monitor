package com.main.carbon_emission_monitor.enums;

public enum ErrorCodeEnums {

    SYSTEM_EXCEPTION("SYSTEM_ERROR", "系统异常", ResultCode.SYSTEM_UNKNOWN_ERROR),
    PARAM_VALIDATE_ERROR("PARAM_VALIDATE_ERROR", "参数校验异常", ResultCode.PARAM_VALIDATE_ERROR),
    USERNAME_EXIST_ERROR("USERNAME_EXIST_ERROR","用户已经存在",ResultCode.USERNAME_EXIST_ERROR),

    //登录异常
    USER_NOT_EXIST_OR_PASSWORD_ERROR("USER_NOT_EXIST_OR_PASSWORD_ERROR","用户名或密码错误",ResultCode.USER_NOT_EXIST_OR_PASSWORD_ERROR),

    //权限禁止
    FORBIDDEN_ERROR("FORBIDDEN_ERROR","禁止访问",ResultCode.FORBIDDEN_ERROR),

    //参数异常
    REQUEST_PARAM_ERROR("REQUEST_PARAM_ERROR","参数异常",ResultCode.REQUEST_PARAM_ERROR);

    private final String name;

    /**
     * 错误描述
     */
    private final String desc;

    /**
     * 结果编码
     */
    private int code;

    ErrorCodeEnums(String name, String desc, int code) {
        this.name = name;
        this.desc = desc;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
