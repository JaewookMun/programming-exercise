package com.example.jpaboard.controller.dto;

import com.example.jpaboard.domain.Board;
import lombok.Data;

@Data
public class DetailBoardDto extends BoardDto {

    private String content;

    public DetailBoardDto(Board board) {
        super(board);
        this.content = board.getContent();
    }

    public DetailBoardDto increaseViewCount() {
        setViewCount(getViewCount()+1);
        return this;
    }
}
