package com.main.carbon_emission_monitor.dto.basic;

public enum ErrorCodeEnums {

    SYSTEM_EXCEPTION("SYSTEM_ERROR", "系统异常", ResultCode.SYSTEM_UNKNOWN_ERROR),
    PARAM_VALIDATE_ERROR("PARAM_VALIDATE_ERROR", "参数校验异常", ResultCode.PARAM_VALIDATE_ERROR),
    USERNAME_EXIST_ERROR("USERNAME_EXIST_ERROR","用户已经存在",ResultCode.USERNAME_EXIST_ERROR),
    FORBIDDEN_ERROR("FORBIDDEN_ERROR","禁止访问",ResultCode.FORBIDDEN_ERROR);


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
