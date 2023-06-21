package com.wzc.bookcontrol.dao.request;

import lombok.Data;

/**
 * @author wzc
 * 2023/1/26
 */
@Data
public class UserRegister {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkWord;
}
