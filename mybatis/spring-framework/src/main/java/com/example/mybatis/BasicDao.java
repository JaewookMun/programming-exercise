package com.example.mybatis;

import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface BasicDao {
    List<User> selectAll();
}
