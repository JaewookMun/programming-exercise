package com.example.jpaboard.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardRegisterForm {

    private String writer;
    private String password;

    private String title;
    private String content;

    @Builder
    public BoardRegisterForm(String writer, String password, String title, String content) {
        this.writer = writer;
        this.password = password;
        this.title = title;
        this.content = content;
    }
}
