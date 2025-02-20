package com.example.treeblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép tất cả endpoint
                .allowedOrigins("http://localhost:3001") // Địa chỉ frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Phương thức HTTP được phép
                .allowedHeaders("*") // Tất cả các header
                .allowCredentials(true); // Cho phép cookie (nếu cần)
    }
}