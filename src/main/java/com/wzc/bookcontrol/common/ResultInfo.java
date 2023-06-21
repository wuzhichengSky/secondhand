package com.wzc.bookcontrol.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wzc
 * 2023/1/26
 *
 * 通用返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo<T> implements Serializable {
    private int code;

    private T data;

    private String message;


    public static ResultInfo success(Object data){
        return new ResultInfo(200,data,"成功");
    }

    public static ResultInfo success(Object data,String message){
        return new ResultInfo(200,data,message);
    }

    public static ResultInfo success(String message){
        return new ResultInfo(200,null,message);
    }

    public static ResultInfo fail(ErrorCode errorCode){
        return new ResultInfo(errorCode.getCode(),null,errorCode.getMessage());
    }

    public static ResultInfo fail(int code,String message){
        return new ResultInfo(code,null,message);
    }

    public static ResultInfo fail(ErrorCode e,String message){
        return new ResultInfo(e.getCode(),null,message);
    }
}
