package com.example.venue.config;

import com.example.venue.component.AdminInterceptor;
import com.example.venue.component.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/login.html", "/getCode", "/loginSubmit", "/logout", "/error", "/**/*.js", "/**/*.css", "/**/*.json", "/**/*.png");
        registry.addInterceptor(new AdminInterceptor())
                .excludePathPatterns("/limit.html", "/login.html","/getCode", "/loginSubmit", "/logout", "/error", "/**/*.js", "/**/*.css", "/**/*.json", "/**/*.png");
    }
}
