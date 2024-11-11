package com.github.jaewookmun.springevent.spring;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDrivenEventListener {
    /**
     * 해당 리스너의 기본 상태는 동기적 호출이지만,
     * '@Async' 어노테이션을 추가하면 비동기적으로 호출된다.
     */
    @EventListener
    public void handleContextStart(ContextStartedEvent cse) {
        System.out.println("Handling context started event.");
    }
}
