package com.wzc.bookcontrol.vo;

import lombok.Data;

/**
 * @author wzc
 * 2023/1/26
 */
@Data
public class UserVo {
    private Integer uid;

    /**
     * 用户名

     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 0--女  1--男
     */
    private Integer gender;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private String phone;

    /**
     * 0--普通用户  1--管理员
     */
    private Integer role;

    private String token;
}
