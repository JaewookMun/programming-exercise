package com.example.mybatis.dao;

import com.example.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface BasicDao {
    List<User> selectAll();
}
