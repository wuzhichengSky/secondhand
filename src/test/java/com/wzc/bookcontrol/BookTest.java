package com.wzc.bookcontrol;

import com.wzc.bookcontrol.dao.pojo.Book;
import com.wzc.bookcontrol.mapper.BookMapper;
import com.wzc.bookcontrol.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author wzc
 * 2023/2/3
 */
@SpringBootTest
public class BookTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Test
    void getBookTest(){
        List<Book> book = bookMapper.selectList(null);
        System.out.println(book);
    }
}
