package com.wzc.bookcontrol.common;

/**
 * @author wzc
 * 2023/1/26
 *
 * 通用错误码
 */
public enum ErrorCode {
    NO_LOGIN(4001,"未登录"),
    SYSTEM_ERROR(4002,"系统错误"),
    PARAMS_ERROR(4003,"参数有误"),
    TOKEN_ERROR(10003, "token不合法");

    private final int code;
    private final String message;

    ErrorCode(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
