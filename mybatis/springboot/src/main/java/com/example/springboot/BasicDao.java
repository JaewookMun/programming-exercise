package com.example.springboot;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicDao {

    public List<User> selectAll();
}
