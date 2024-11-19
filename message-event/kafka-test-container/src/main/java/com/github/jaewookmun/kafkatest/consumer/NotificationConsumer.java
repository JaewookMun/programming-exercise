package com.github.jaewookmun.kafkatest.consumer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
public class NotificationConsumer {

    private String recentNotification;

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(String notification) {
        recentNotification = notification;
        log.info("Received notification: {}", notification);
    }
}
