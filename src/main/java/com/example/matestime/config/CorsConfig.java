package com.example.matestime.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Apply to all API endpoints
                .allowedOrigins("http://localhost:8888")     // Allow all origins (same as previous current @CrossOrigin)
                .allowedMethods("*")  // Allow common HTTP methods
                .allowedHeaders("*")     // Allow all headers
                .allowCredentials(false); // Set to false when using allowedOrigins("*")
    }


}
