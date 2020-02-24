package com.lhj.keepgym.members;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class KeepgymMembersWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepgymMembersWebApplication.class, args);
    }

}
