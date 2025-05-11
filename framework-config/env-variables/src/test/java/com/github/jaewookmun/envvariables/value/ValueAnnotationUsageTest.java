package com.github.jaewookmun.envvariables.value;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
//@ActiveProfiles({"prod"})
class ValueAnnotationUsageTest {
    @Autowired
    ValueAnnotationUsage valueAnnotationUsage;

    @Test
    void name() {
        String name = valueAnnotationUsage.getName();
        System.out.println("name = " + name);
    }

}