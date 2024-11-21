package com.github.jaewookmun.springevent.current;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

@SpringBootTest
class SampleEventListenerTest {

    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    void test() {
        SampleEvent sampleEvent = new SampleEvent("test1", LocalDateTime.now());

        publisher.publishEvent(sampleEvent);
    }

}