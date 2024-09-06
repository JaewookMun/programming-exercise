package com.github.jaewookmun.springtest.junit5;

import com.github.jaewookmun.springtest.config.ApplicationConfig;
import com.github.jaewookmun.springtest.config.ServletConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class, ServletConfig.class})
public class IntegrationTest {

    @Autowired
    WebApplicationContext wac;

    @Test
    void tdd() {
        System.out.println("=== JUnit5 ===");
        ServletConfig servletConfig = wac.getBean(ServletConfig.class);
        System.out.println("servletConfig = " + servletConfig);

        Assert.assertNotNull(servletConfig);
    }

}
