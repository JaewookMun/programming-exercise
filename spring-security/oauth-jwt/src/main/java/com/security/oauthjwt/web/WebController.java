package com.security.oauthjwt.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class WebController {


    @GetMapping("/api")
    public ResponseEntity<?> callApiTest() {
        log.info("wow");
        return ResponseEntity.ok("oauth test success");
    }
}