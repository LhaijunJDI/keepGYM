package com.lhj.keepgym;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@SpringBootApplication
public class KeepgymManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepgymManageWebApplication.class, args);
    }

}
