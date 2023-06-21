package com.wzc.bookcontrol.service;

import com.wzc.bookcontrol.dao.pojo.Book;
import com.wzc.bookcontrol.dao.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wzc.bookcontrol.vo.UserVo;

/**
* @author 86188
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-01-26 20:50:27
*/
public interface UserService extends IService<User> {

    void register(String userName, String userPassword, String checkWord);

    UserVo login(String userName, String userPassword);

    UserVo getCurrent(String token);

    void addBook(Book book);

    void sendBook(Integer bid);

    void deleteBook(Integer bid);

    void borrowBook(Integer bid);
}
