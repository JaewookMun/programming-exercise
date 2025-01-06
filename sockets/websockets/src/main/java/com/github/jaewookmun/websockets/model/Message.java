package com.github.jaewookmun.websockets.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Message {
    private String name;

    public Message(String name) {
        this.name = name;
    }
}
