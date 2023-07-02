package com.example.jpaboard.controller.api;

import com.example.jpaboard.controller.dto.BoardDto;
import com.example.jpaboard.controller.dto.BoardUpdate;
import com.example.jpaboard.controller.dto.DetailBoardDto;
import com.example.jpaboard.repository.BoardRepository;
import com.example.jpaboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("/boards")
    public Page<BoardDto> boards(Pageable pageable) {
        System.out.println("page: " + pageable.getPageNumber());
        System.out.println("size: " + pageable.getPageSize());
        System.out.println("sort: " + pageable.getSort());

        return boardRepository.findAll(pageable).map(BoardDto::new);
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<DetailBoardDto> viewBoard(@PathVariable Long id) {
        DetailBoardDto detailBoardDto = boardService.viewDetail(id);

        return ResponseEntity.ok(detailBoardDto);
    }

    @PostMapping("/boards/update")
    public ResponseEntity update(@RequestBody BoardUpdate boardUpdate) {
        boardService.modify(boardUpdate.getId(), boardUpdate);

        return ResponseEntity.ok().build();
    }
}
