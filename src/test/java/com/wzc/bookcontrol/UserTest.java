package com.wzc.bookcontrol;

import com.wzc.bookcontrol.dao.pojo.User;
import com.wzc.bookcontrol.service.UserService;
import com.wzc.bookcontrol.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wzc
 * 2023/1/26
 */
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    void registerTest(){
        userService.register("张三","12345678","12345678");
        System.out.println(123);
    }

    @Test
    void loginTest(){
        UserVo vo = userService.login("张三", "12345678");
        System.out.println(vo);
    }

    @Test
    void getUserTest(){
        User user = userService.getById(1);
        System.out.println(user);
    }
}
