package com.github.jaewookmun.annotationconfig.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "com.github.jaewookmun.annotationconfig"
)
//public class ServletConfig extends WebMvcConfigurerAdapter {
public class ServletConfig
{

}
