package com.github.jaewookmun.kafkatest.model;

import java.sql.Timestamp;
import java.time.Instant;

public abstract class Notification {
    public Timestamp getTimestamp() {
        return Timestamp.from(Instant.now());
    }

    @Override
    public String toString() {
        return String.format("timestamp: %s", getTimestamp());
    }
}
