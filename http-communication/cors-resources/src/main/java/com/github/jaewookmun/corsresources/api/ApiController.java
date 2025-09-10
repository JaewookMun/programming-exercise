package com.github.jaewookmun.corsresources.api;

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
}
