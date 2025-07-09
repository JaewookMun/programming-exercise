package com.github.jaewookmun.testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FruitRepositoryTest {
    @Autowired
    FruitRepository fruitRepository;

    @Test
    void save() {
        Fruit fruit = new Fruit("Apple", "green");
        fruitRepository.save(fruit);
    }
}