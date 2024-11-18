package com.github.jaewookmun.springevent.generic;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
//public class GenericSpringEventListener implements ApplicationListener<GenericSpringEvent<String>> {
public class AnnotationDrivenEventListener {
    @EventListener(condition = "#event.success")
    public void handleSuccessful(@NonNull GenericSpringEvent<String> event) {
        System.out.println("Handling generic event. (conditional)");
    }
}
