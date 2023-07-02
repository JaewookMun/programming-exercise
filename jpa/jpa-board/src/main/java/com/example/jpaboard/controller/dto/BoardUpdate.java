package com.example.jpaboard.controller.dto;

import lombok.Data;

@Data
public class BoardUpdate {

    private Long id;
    private String title;
    private String content;
}
