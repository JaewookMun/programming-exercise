package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BasicServiceTest {

    @Autowired private BasicService basicService;

    @Test
    public void test() {
        basicService.selectUsers().forEach(u -> {
            System.out.println("name = " + u.getName());
            System.out.println("loginId = " + u.getLoginId());
        });
    }
}