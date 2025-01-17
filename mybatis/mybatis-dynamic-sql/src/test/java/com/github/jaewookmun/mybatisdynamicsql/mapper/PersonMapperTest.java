package com.github.jaewookmun.mybatisdynamicsql.mapper;

import com.github.jaewookmun.mybatisdynamicsql.domain.PersonRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
//@Sql(scripts = {"/sql/ddl.sql"})
class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    void insertTest() {
        PersonRecord person = new PersonRecord();
        person.setId(2);
        person.setFirstName("Kim");
        person.setLastName("");
        person.setBirthDate(LocalDateTime.now());
        person.setEmployed(true);
        person.setOccupation("doctor");
        person.setAddressId(1);

        personMapper.insert(person);

        Optional<PersonRecord> byId = personMapper.findById(1);
        System.out.println(byId.get());

        personMapper.findAll().forEach(System.out::println);

    }
}