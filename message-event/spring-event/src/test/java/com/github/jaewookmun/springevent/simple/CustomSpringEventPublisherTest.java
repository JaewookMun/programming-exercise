package com.github.jaewookmun.springevent.simple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomSpringEventPublisherTest {
    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;

    @Test
    void basic() {
        customSpringEventPublisher.publishEvent("[test]: default event");
    }
}