package com.flow.main.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/15
 */
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/*", "classpath:/public/"};

    /**
     * 添加视图控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问：localhost:8080/index.html或者localhost:8080/，都跳转到 classpath:/templates/index.html
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        // 浏览器访问：localhost:8080/main.html 跳转到 classpath:/templates/main.html
        registry.addViewController("/main.html").setViewName("main");
        registry.addViewController("/login/validate").setViewName("main");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            if (!registry.hasMappingForPattern("/webjars/**")) {
                registry.addResourceHandler("/webjars/**").addResourceLocations(
                        "classpath:/META-INF/resources/webjars/");
            }
            if (!registry.hasMappingForPattern("/**")) {
                registry.addResourceHandler("/**").addResourceLocations(
                        CLASSPATH_RESOURCE_LOCATIONS);
            }
            if (!registry.hasMappingForPattern("/static/**")) {
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
