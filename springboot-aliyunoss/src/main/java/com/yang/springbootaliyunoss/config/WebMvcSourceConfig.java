package com.yang.springbootaliyunoss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebMvcSourceConfig
 * @Author: XQ-Yang
 * @Date: 2022/11/25 0025
 * @Description:
 **/
@Configuration
public class WebMvcSourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("D:/upload");
    }
}
