package com.github.jaewookmun.springevent.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ContextRefreshedListenerTest {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    WebApplicationContext wac;

    @Test
    void annotation() {
        ContextStartedEvent contextEvent = new ContextStartedEvent(wac);
        applicationEventPublisher.publishEvent(contextEvent);
    }
}