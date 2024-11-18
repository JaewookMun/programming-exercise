package com.github.jaewookmun.springevent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserEvent {
    private String name;
    private EventType eventType;

    public enum EventType {CREATE}
}


