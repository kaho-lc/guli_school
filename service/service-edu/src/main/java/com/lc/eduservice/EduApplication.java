package com.lc.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//创建启动类
@SpringBootApplication
//包扫描规则:设置扫描com.lc目录下的文件
@ComponentScan(basePackages = {"com.lc"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}