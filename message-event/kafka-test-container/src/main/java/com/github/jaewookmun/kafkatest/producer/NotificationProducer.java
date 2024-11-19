package com.github.jaewookmun.kafkatest.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jaewookmun.kafkatest.model.LocationNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public String produce() throws JsonProcessingException {
        LocationNotification notification = new LocationNotification();
        notification.setLongitude("127");
        notification.setLatitude("37");

        String json = objectMapper.writeValueAsString(notification);
        kafkaTemplate.send(topic, json);

        return json;
    }
}
