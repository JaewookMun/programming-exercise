package com.github.jaewookmun.hellokafka.api;

import com.github.jaewookmun.hellokafka.producer.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final KafkaMessageProducer producer;

    @PostMapping("/publish")
    public void messageToTopic(@RequestParam("message") String message) {
        producer.send(message);
    }
}
