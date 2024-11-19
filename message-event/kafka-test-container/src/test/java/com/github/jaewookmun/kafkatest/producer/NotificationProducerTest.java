package com.github.jaewookmun.kafkatest.producer;

import com.github.jaewookmun.kafkatest.consumer.NotificationConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.kafka.consumer.auto-offset-reset=earliest",
        }
)
@Testcontainers
class NotificationProducerTest {

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
        DockerImageName.parse("confluentinc/cp-kafka:latest")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    NotificationProducer producer;

    @Autowired
    NotificationConsumer consumer;

    @Test
    void test() throws Exception {
        String produce = producer.produce();
        System.out.println("produce = " + produce);

        Thread.sleep(1000);

        String recentNotification = consumer.getRecentNotification();
        System.out.println("recentNotification = " + recentNotification);

        assertThat(produce).isEqualTo(recentNotification);
    }
}