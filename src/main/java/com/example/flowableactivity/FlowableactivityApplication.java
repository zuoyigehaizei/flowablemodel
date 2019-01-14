package com.example.flowableactivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.flowableactivity.mapper")
public class FlowableactivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableactivityApplication.class, args);
    }

}

