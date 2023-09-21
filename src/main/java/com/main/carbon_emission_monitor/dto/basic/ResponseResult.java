package com.main.carbon_emission_monitor.dto.basic;


import java.io.Serial;
import java.io.Serializable;

public class ResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 958295628567280402L;


    private final int code;
    private String msg;
    private T data;




    public ResponseResult() {
        code = ResultCode.SUCCESS;
    }


    public ResponseResult(T data) {
        this(ResultCode.SUCCESS, null,data);
    }

    public ResponseResult(int code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>();
    }


    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(data);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<T>();
    }


    public static <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<T>(ErrorCodeEnums.SYSTEM_EXCEPTION.getCode(), msg);
    }


    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}



