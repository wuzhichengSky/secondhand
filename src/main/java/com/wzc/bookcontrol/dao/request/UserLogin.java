package com.wzc.bookcontrol.dao.request;

import lombok.Data;

/**
 * @author wzc
 * 2023/1/26
 */
@Data
public class UserLogin {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

}
