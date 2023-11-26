package com.main.carbon_emission_monitor.dto.basic;


import com.main.carbon_emission_monitor.enums.ResultCode;

import java.io.Serializable;

public class ResponseResult<T> implements  Serializable{

    public  int code;
    public String msg;
    public T data;




    public ResponseResult() {
        code = ResultCode.SUCCESS;
    }

    public ResponseResult(T data) {
        this(ResultCode.SUCCESS, null,data);
    }
//
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



    public static <T> ResponseResult<T> fail(int code,String msg) {
        return new ResponseResult<T>(code, msg);
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



