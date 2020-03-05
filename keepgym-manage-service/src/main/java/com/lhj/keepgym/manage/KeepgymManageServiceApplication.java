package com.lhj.keepgym.manage;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages = "com.lhj.keepgym.manage.mapper")
public class KeepgymManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepgymManageServiceApplication.class, args);
    }

}
