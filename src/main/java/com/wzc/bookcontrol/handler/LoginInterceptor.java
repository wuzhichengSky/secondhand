package com.wzc.bookcontrol.handler;


import com.wzc.bookcontrol.common.ErrorCode;
import com.wzc.bookcontrol.common.exception.BusinessException;
import com.wzc.bookcontrol.dao.pojo.User;
import com.wzc.bookcontrol.service.UserService;
import com.wzc.bookcontrol.utils.JwtHelper;
import com.wzc.bookcontrol.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guoyixing
 * 2023/1/24
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String token= request.getHeader("token");
        // 执行认证
        if (token == null) {
            throw new BusinessException(ErrorCode.NO_LOGIN);
        }

        // 验证 token
        if (JwtHelper.isExpiration(token)) {
            throw new BusinessException(ErrorCode.TOKEN_ERROR);
        }

        // 获取 token 中的 user id
        Long userId = JwtHelper.getUserId(token);
        //查询数据库，看看是否存在此用户
        User user = userService.getById(userId);
        if(user==null){
            throw new BusinessException(ErrorCode.TOKEN_ERROR,"用户不存在");
        }
        //将获取到的user存入UserThreadLocal中备用
        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        UserThreadLocal.remove();
    }
}
