package com.github.jaewookmun.kafkatest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class LocationNotification extends Notification {
    private String longitude;
    private String latitude;
}
