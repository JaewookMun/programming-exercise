package com.github.jaewookmun.kafkatest;

import org.springframework.boot.SpringApplication;

public class TestKafkaTestContainerApplication {

	public static void main(String[] args) {
		SpringApplication.from(KafkaTestContainerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
