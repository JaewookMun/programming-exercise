package com.github.jaewookmun.hellokafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "hello-kafka")
    public void consume(String message) {
        log.info("consumer consume the message: {}", message);
    }
}
