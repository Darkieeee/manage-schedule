package com.example.springkafka;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebResourceConfigure implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("../resources/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("../resources/static/js/");
    }
}
