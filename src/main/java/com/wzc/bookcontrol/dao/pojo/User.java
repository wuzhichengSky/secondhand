package com.wzc.bookcontrol.dao.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 0--否  1--是
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updataTime;

    /**
     * 验证密码
     */
    private String checkWord;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}