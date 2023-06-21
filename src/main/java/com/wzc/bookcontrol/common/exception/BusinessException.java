package com.wzc.bookcontrol.common.exception;

import com.wzc.bookcontrol.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzc
 * 2023/1/26
 *
 * 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException{

    int code;

    String message;

    public BusinessException(int code,String message){
        this.code=code;
        this.message=message;
    }

    public BusinessException(ErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }

    public BusinessException(ErrorCode errorCode,String message){
        this.code=errorCode.getCode();
        this.message=message;
    }
}
