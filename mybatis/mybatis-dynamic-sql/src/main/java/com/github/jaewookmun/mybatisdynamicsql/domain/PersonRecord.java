package com.github.jaewookmun.mybatisdynamicsql.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class PersonRecord {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private Boolean employed;
    private String occupation;
    private Integer addressId;
}
