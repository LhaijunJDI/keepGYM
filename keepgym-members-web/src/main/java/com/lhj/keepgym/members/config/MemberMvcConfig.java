package com.lhj.keepgym.members.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc视图配置器
 * @author Shinelon
 */

@Configuration
public class MemberMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("index");
        registry.addViewController("/memberGym").setViewName("memberIndex");
        registry.addViewController("/managerGym").setViewName("memberInfo");
        registry.addViewController("/memberInfo").setViewName("memberInfo");
    }

}
