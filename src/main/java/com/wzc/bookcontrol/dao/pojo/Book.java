package com.wzc.bookcontrol.dao.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 书籍表
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer bid;

    /**
     * 
     */
    private String bookName;

    /**
     * 
     */
    private String author;

    /**
     * 出版时间 2020-10-22
     */
    private String publishTime;

    /**
     * 
     */
    private Integer price;

    /**
     * 状态 0-待借  1-已借出 2-审核中
     */
    private Integer state;

    /**
     * 拥有者id
     */
    private Integer ownerId;

    /**
     * 借出者id
     */
    private Integer borId;

    /**
     * 
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
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}