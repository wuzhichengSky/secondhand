package com.wzc.bookcontrol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wzc.bookcontrol.mapper")
public class BookcontrolApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookcontrolApplication.class, args);
    }

}
