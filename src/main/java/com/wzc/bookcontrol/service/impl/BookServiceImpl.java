package com.wzc.bookcontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzc.bookcontrol.dao.pojo.Book;
import com.wzc.bookcontrol.service.BookService;
import com.wzc.bookcontrol.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author 86188
* @description 针对表【book(书籍表)】的数据库操作Service实现
* @createDate 2023-01-26 20:08:51
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

}




