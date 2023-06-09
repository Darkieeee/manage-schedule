package com.example.springkafka.resource;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("../static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("../static/js/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("../static/images/");
    }
}
