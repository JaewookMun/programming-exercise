package study.jaewook.springreact.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/server/time")
    public String serverTime() {
        log.info("serverTime");

        return LocalDateTime.now().toString();
    }
}
