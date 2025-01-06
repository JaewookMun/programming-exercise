package com.github.jaewookmun.websockets.model;

import lombok.Getter;

@Getter
public class Greeting {
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
}
