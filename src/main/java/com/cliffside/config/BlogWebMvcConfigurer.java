package com.cliffside.config;

import com.cliffside.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web应用的配置类
 * @author cliffsdie
 */
@Configuration
public class BlogWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    /**
     * 添加一个拦截器，部分文件不可访问
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加一个拦截器，拦截/admin为前缀的url
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");
    }

    /**
     * 添加资源控制
     * @param registry
     */
    @Override
    public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").
                addResourceLocations("file:/home/project/upload/");
    }

}
