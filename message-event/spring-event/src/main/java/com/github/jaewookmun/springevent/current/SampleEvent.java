package com.github.jaewookmun.springevent.current;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleEvent {
    private String name;
    private LocalDateTime timestamp;
}
