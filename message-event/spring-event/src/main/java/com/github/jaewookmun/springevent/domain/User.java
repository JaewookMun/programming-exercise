package com.github.jaewookmun.springevent.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class User {
    @Id @GeneratedValue
    private Long id;

    private String name;

    public User(String name) {
        this.name = name;
    }
}
