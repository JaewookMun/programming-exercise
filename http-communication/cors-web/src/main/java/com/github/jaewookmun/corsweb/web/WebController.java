package com.github.jaewookmun.corsweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/cors-page")
    public String corsPage() {
        return "cors-page";
    }
}
