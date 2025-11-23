package com.example.data.analysis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/20 13:23
 */

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
/**
 * 重写添加资源处理器的方法
 * 用于配置静态资源访问路径和API文档访问路径
 * @param registry 资源处理器注册表，用于注册静态资源访问路径
 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    // 配置静态资源访问路径，所有请求/**都指向classpath:/static/目录
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /**
         * 配置swagger-ui显示文档
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
