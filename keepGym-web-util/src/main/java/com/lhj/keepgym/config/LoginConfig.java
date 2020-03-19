package com.lhj.keepgym.config;

import com.lhj.keepgym.intercepters.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/main", "/login/**", "/login","/members/**","/error","/img/**","/css/**","/js/**","/images/**","/fonts/**","/javascript/**")
        ;
    }
}
