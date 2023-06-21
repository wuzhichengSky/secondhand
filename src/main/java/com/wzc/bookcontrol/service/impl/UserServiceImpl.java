package com.wzc.bookcontrol.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzc.bookcontrol.common.ErrorCode;
import com.wzc.bookcontrol.common.exception.BusinessException;
import com.wzc.bookcontrol.dao.pojo.Book;
import com.wzc.bookcontrol.dao.pojo.User;
import com.wzc.bookcontrol.service.BookService;
import com.wzc.bookcontrol.service.UserService;
import com.wzc.bookcontrol.mapper.UserMapper;
import com.wzc.bookcontrol.utils.JwtHelper;
import com.wzc.bookcontrol.utils.UserThreadLocal;
import com.wzc.bookcontrol.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.LinkedList;
import java.util.List;

/**
* @author 86188
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-01-26 20:50:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private BookService bookService;

    public static String SALT="123456";     //盐

    @Override
    public void register(String userName, String userPassword, String checkWord) {

        //1.校验
        //为空
        if(StringUtils.isAnyBlank(userName,userPassword))
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码为空");
        //密码与校验密码不同
        if(!userPassword.equals(checkWord)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码与校验密码不同");
        }
        //密码少于8位
        if(userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过短");
        }
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        long count = this.count(wrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户已存在");
        }
        //2.对密码加密
        String word = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3.存储到数据库中
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(word);
        //自动注册为普通用户
        user.setRole(0);
        boolean save = save(user);
        if(!save){
            throw new RuntimeException();
        }
    }

    @Override
    public UserVo login(String userName, String userPassword) {
        //1.校验
        //非空
        if(StringUtils.isAnyBlank(userName,userPassword))
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名或密码为空");
        if(userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过短");
        }

        //2.核对密码
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        User user = this.getOne(wrapper);
        String newWord=DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes());
        if(!newWord.equals(user.getUserPassword()))
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码错误");

        //3.生成token
        String token = JwtHelper.createToken(user.getUid().longValue());
        //4.脱敏返回
        User safeUser = getSafeUser(user);
        UserVo userVo= copy(safeUser);
        userVo.setToken(token);
        return userVo;
    }

    @Override
    public UserVo getCurrent(String token) {
        User user = UserThreadLocal.get();
        //TODO 返回用户没有加密的密码
        return copy(user);
    }

    @Override
    public void addBook(Book book) {
        //将书籍的拥有者id owner_id标为用户id
        User user = UserThreadLocal.get();
        Integer uid = user.getUid();
        book.setOwnerId(uid);
        //将书籍的state标为 3--未上架
        book.setState(3);
        if(!bookService.save(book))
            throw new RuntimeException();
    }

    @Override
    public void sendBook(Integer bid) {
        Book book = bookService.getById(bid);
        book.setState(2);
        bookService.updateById(book);
    }

    @Override
    public void deleteBook(Integer bid) {
        Book book = bookService.getById(bid);
        book.setState(3);
        bookService.updateById(book);
    }

    @Override
    public void borrowBook(Integer bid) {
        User user = UserThreadLocal.get();
        Integer uid = user.getUid();
        Book book = bookService.getById(bid);
        //将书籍的借有者id bor_id标为用户id
        book.setBorId(uid);
        //将书籍的state标为 1--已借出
        book.setState(1);
        bookService.updateById(book);
    }

    /**
     * 脱敏（脱去敏感信息--密码）
     */
    public User getSafeUser(User user){
        User u=new User();
        u.setUid(user.getUid());
        u.setUserName(user.getUserName());
        u.setAge(user.getAge());
        u.setGender(user.getGender());
        u.setPhone(user.getPhone());
        u.setRole(user.getRole());
        return u;
    }

    public List<UserVo> copyList(List<User> users){
        LinkedList<UserVo> userVos = new LinkedList<>();
        for (User user : users) {
            UserVo userVo = copy(user);
            userVos.add(userVo);
        }
        return userVos;
    }

    public UserVo copy(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}




