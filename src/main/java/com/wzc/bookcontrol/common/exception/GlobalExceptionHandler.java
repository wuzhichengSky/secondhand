package com.wzc.bookcontrol.common.exception;


import com.wzc.bookcontrol.common.ErrorCode;
import com.wzc.bookcontrol.common.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author  wzc
 */

@RestControllerAdvice   //spring AOP
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResultInfo businessExceptionHandler(BusinessException e){
      return ResultInfo.fail(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultInfo runtimeExceptionHandler(RuntimeException e){
        log.error("系统异常:",e);
        return ResultInfo.fail(ErrorCode.SYSTEM_ERROR,e.getMessage());
    }
}
