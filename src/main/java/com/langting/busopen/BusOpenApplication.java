package com.langting.busopen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.langting.busopen.mapper")
public class BusOpenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusOpenApplication.class, args);
    }

}
