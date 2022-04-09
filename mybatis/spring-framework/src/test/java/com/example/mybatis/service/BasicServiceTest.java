package com.example.mybatis.service;

import com.example.mybatis.BasicService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class BasicServiceTest {

    @Autowired
    private BasicService basicService;

    @Test
    public void test() {
        basicService.selectAll().forEach(u -> {
            System.out.println("user: " + u.getName());
        });
    }
}

