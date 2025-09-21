package com.github.jaewookmun.corsresources.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cors")
public class ApiController {

    @GetMapping("/simple-request")
    public String simpleRequest() {
        return "simple request";
    }

    @GetMapping("preflighted-request")
    public String preflightedRequest(HttpServletRequest request) {
        return "preflighted request: " + request.getHeader("Custom-Header-Text");
    }

    @GetMapping("/credentialed-request")
    public String credentialedRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            sb.append(cookie.getName()).append("=").append(cookie.getValue()).append(", ");
        }

        return "credentialed request: " + sb;
    }
}
