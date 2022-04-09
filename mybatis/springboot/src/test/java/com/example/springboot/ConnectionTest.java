package com.example.springboot;

import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class ConnectionTest {

    @Autowired
    private ApplicationContext ac;


    @Test
    public void autoCreation() throws SQLException {
        //given
        DataSource dataSource = ac.getBean(DataSource.class);
        SqlSessionFactory sqlSessionFactory = ac.getBean(SqlSessionFactory.class);

        //when
        Connection connection = dataSource.getConnection();

        //then
        System.out.println("connection = " + connection);
        System.out.println("sqlSessionFactory = " + sqlSessionFactory);

        Assertions.assertThat(sqlSessionFactory).isNotNull();

    }
}
