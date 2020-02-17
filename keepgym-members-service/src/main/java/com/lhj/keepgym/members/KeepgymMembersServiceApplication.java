package com.lhj.keepgym.members;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = "com.lhj.keepgym.members.mapper")
public class KeepgymMembersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepgymMembersServiceApplication.class, args);
    }

}
