package com.wzc.bookcontrol.controller;

import com.wzc.bookcontrol.common.ErrorCode;
import com.wzc.bookcontrol.common.ResultInfo;
import com.wzc.bookcontrol.common.exception.BusinessException;
import com.wzc.bookcontrol.dao.pojo.Book;
import com.wzc.bookcontrol.dao.pojo.User;
import com.wzc.bookcontrol.dao.request.UserLogin;
import com.wzc.bookcontrol.dao.request.UserRegister;
import com.wzc.bookcontrol.service.UserService;
import com.wzc.bookcontrol.utils.JwtHelper;
import com.wzc.bookcontrol.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wzc
 * 2023/1/26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public ResultInfo register(@RequestBody UserRegister register){
        String userName=register.getUserName();
        String userPassword = register.getUserPassword();
        String checkWord=register.getCheckWord();

        if(StringUtils.isAnyBlank(userName,userPassword))
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        userService.register(userName,userPassword,checkWord);
        return ResultInfo.success("用户注册成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultInfo login(@RequestBody UserLogin login){
        String userName=login.getUserName();
        String userPassword = login.getUserPassword();

        if(StringUtils.isAnyBlank(userName,userPassword))
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        UserVo userVo=userService.login(userName,userPassword);
        return ResultInfo.success(userVo,"登录成功");
    }

    /**
     * 获取当前用户的信息
     */
    @GetMapping("/getCurrent")
    public ResultInfo getCurrent(HttpServletRequest request){
        String token = request.getHeader("token");
        UserVo userVo=userService.getCurrent(token);
        return ResultInfo.success(userVo);
    }

    /**
     * 上传书籍
     */
    @PostMapping("/addBook")
    public ResultInfo addBook(@RequestBody Book book){
        userService.addBook(book);
        return ResultInfo.success("上传成功");
    }

    /**
     * 书籍送审
     */
    @PutMapping("/sendBook")
    public ResultInfo sendBook(@RequestBody Book book){
        Integer bid = book.getBid();
        userService.sendBook(bid);
        return ResultInfo.success("送审成功");
    }

    /**
     * 撤销书籍
     */
    @DeleteMapping("/deleteBook")
    public ResultInfo deleteBook(@RequestBody Book book){
        Integer bid = book.getBid();
        userService.deleteBook(bid);
        return ResultInfo.success("撤销成功");
    }

    /**
     * 借阅书籍
     */
    @PutMapping("/borrowBook")
    public ResultInfo borrowBook(@RequestBody Book book){
        Integer bid = book.getBid();
        userService.borrowBook(bid);
        return ResultInfo.success("借阅成功");
    }
}
