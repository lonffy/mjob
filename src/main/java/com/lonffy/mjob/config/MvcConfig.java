package com.lonffy.mjob.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    /**
     * 添加资源处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //Springboot默认静态资源路径为：classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
        //通过addResourceHandlers 可以自定义静态资源路径
        registry.addResourceHandler("/**/*.html")
                .addResourceLocations("classpath:/pages/");
        // 这是请求url的匹配模式，匹配url根路径下的所有路径（包括子路径，如果只有一个*，那就不包括子路径）
        registry.addResourceHandler("/static/**")
                // 这是文件路径的匹配模式，值上面匹配的路径在这个文件夹下面找文件
                .addResourceLocations("classpath:/static/");
    }
}