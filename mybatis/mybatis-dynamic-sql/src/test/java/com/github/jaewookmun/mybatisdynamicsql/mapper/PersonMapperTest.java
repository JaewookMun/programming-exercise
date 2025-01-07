package com.github.jaewookmun.mybatisdynamicsql.mapper;

import com.github.jaewookmun.mybatisdynamicsql.domain.PersonRecord;
import org.junit.jupiter.api.Test;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/sql/ddl.sql"})
class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    void insertTest() {
        PersonRecord person = new PersonRecord();
        person.setId(1);
        person.setFirstName("Kim");
        person.setLastName("");
        person.setBirthDate(LocalDateTime.now());
        person.setEmployed(true);
        person.setOccupation("doctor");
        person.setAddressId(1);

        personMapper.insert(person);
    }

}