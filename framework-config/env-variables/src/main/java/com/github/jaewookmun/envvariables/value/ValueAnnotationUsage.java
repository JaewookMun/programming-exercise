package com.github.jaewookmun.envvariables.value;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class ValueAnnotationUsage {
    @Value("${spring.application.name}")
    private String name;
}
