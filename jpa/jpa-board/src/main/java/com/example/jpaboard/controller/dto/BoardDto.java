package com.example.jpaboard.controller.dto;


import com.example.jpaboard.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long boardId;
    private String writer;
    private String title;
    private int viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public BoardDto(Board board) {
        boardId = board.getId();
        writer = board.getMember().getName();
        title = board.getTitle();
        viewCount = board.getViewCount();
        createdDate = board.getCreatedDate();
        modifiedDate = board.getModifiedDate();
    }
}
