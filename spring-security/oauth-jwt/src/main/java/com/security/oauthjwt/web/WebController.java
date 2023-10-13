package com.security.oauthjwt.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class WebController {

    @GetMapping("/api")
    public ResponseEntity<?> callApiTest() {
        log.info("wow");
        return ResponseEntity.ok("oauth test success");
    }

    @GetMapping("/oauth2/naver")
    public ResponseEntity<?> naver() {
        Map<String, String> response = new HashMap<>();
        String url = "http://localhost:8080/oauth2/authorization/naver";
        response.put("server", "naver");
        response.put("url", url);

        return ResponseEntity.ok(response);
    }
}