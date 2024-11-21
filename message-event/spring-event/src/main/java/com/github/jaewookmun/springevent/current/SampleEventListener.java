package com.github.jaewookmun.springevent.current;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleEventListener {

    @Order(1)
    @EventListener
    public void handleSampleEvent(final SampleEvent sampleEvent) {
        log.info("HANDLE: {}", sampleEvent);
    }

    @Order(2)
    @EventListener
    public void handleSampleEvent2(final SampleEvent sampleEvent) {
        log.info("HANDLE SECOND: {}", sampleEvent);
    }
}
