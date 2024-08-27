package com.github.jaewookmun.annotationconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * Spring 버전 5.0 (Spring Boot 2) 부터 추상클래스 WebMvcConfigurerAdapter의 사용은 Deprecated 된다. <br>
 * [참고] - https://www.baeldung.com/web-mvc-configurer-adapter-deprecated <br>
 * <br>
 *
 * WebMvcConfigurer 인터페이스를 직접 구현하여 사용하는 것이 권장된다.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "com.github.jaewookmun.annotationconfig"
)
public class ServletConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
}
