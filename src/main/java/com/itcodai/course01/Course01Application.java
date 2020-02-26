package com.itcodai.course01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itcodai.course01.dao")
public class Course01Application {

    public static void main(String[] args) {
        SpringApplication.run(Course01Application.class, args);
    }

}
