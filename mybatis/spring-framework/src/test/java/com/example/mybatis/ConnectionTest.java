package com.example.mybatis;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class ConnectionTest {


    @Test
    public void test(ApplicationContext ac) throws SQLException {
        DataSource dataSource = ac.getBean(DataSource.class);
        MapperFactoryBean bean = ac.getBean(MapperFactoryBean.class);
        SqlSessionTemplate bean1 = ac.getBean(SqlSessionTemplate.class);
        TransactionManager bean2 = ac.getBean(TransactionManager.class);
        System.out.println("bean = " + bean);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        System.out.println("dataSource.getConnection() = " + dataSource.getConnection());

    }
}
