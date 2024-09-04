package com.github.jaewookmun.springtest;

import com.github.jaewookmun.springtest.config.ApplicationConfig;
import com.github.jaewookmun.springtest.config.ServletConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class, ServletConfig.class})
public class IntegrationTest
{
    @Autowired
    WebApplicationContext wac;

    @Test
    public void test() {
        ServletConfig servletConfig = wac.getBean(ServletConfig.class);
        System.out.println("servletConfig = " + servletConfig);

        Assert.assertNotNull(servletConfig);
    }
}
