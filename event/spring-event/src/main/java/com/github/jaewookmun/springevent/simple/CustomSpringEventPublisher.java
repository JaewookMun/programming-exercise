package com.github.jaewookmun.springevent.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomSpringEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final String message) {
        System.out.println("Publishing event");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
