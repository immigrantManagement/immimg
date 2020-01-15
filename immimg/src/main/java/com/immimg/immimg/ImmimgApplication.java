package com.immimg.immimg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.immimg.immimg.dao")
public class ImmimgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmimgApplication.class, args);
    }

}
