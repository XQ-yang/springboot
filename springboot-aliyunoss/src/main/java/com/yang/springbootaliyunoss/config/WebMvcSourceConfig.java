package com.yang.springbootaliyunoss.config;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${local.uploadPath}")
    private String fileSavePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+ fileSavePath);
    }
}
