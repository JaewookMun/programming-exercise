package com.example.jpaboard.controller;

import com.example.jpaboard.controller.dto.BoardRegisterForm;
import com.example.jpaboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("init")
    @ResponseBody
    public ResponseEntity init() {

        for(int i=0; i<10; i++) {
            BoardRegisterForm form = BoardRegisterForm.builder()
                    .writer("james")
                    .password("sample123")
                    .title("Spring Data JPA" + (i+1))
                    .content("more difficult than I thought")
                    .build();

            boardService.register(form);
        }

        return ResponseEntity.ok("initialized");
    }
}
