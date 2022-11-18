package com.yang.springbootupanddown.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: 小强
 * @date: 2022/11/17 0017
 * @IDE: IntelliJ IDEA
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yang.springbootupanddown.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置项目基本信息
     */

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("API接口说明文档")
                .termsOfServiceUrl("http://www.example.org")
                //请填写项目联系人信息（名称、网址、邮箱）
                .contact(new Contact("xiaoqiang", "xiaoqiang.com", "yang@163.com"))
                //请填写项目版本号
                .version("1.0")
                .build();
    }
}
